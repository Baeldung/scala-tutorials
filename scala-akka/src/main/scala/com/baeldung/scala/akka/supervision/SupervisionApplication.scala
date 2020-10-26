package com.baeldung.scala.akka.supervision

import java.io.IOException
import java.net.URL

import akka.actor.typed._
import akka.actor.typed.scaladsl.{ActorContext, Behaviors}
import akka.util.Timeout
import com.baeldung.scala.akka.supervision.SupervisionApplication.Filesystem.{FsFind, FsFound}
import com.baeldung.scala.akka.supervision.SupervisionApplication.WebServer.Request

import scala.concurrent.duration.DurationInt
import scala.util.{Failure, Random, Success, Try}

object SupervisionApplication {

  object Main {
    case class Start(id: String, replyTo: ActorRef[Created])
    case class Created(webServer: ActorRef[Request])
    def apply(): Behavior[Start] = {
      Behaviors.receive[Start] { (context, message) =>
        val webServer = context.spawn(WebServer(), message.id)
        context.watch(webServer)
        message.replyTo ! Created(webServer)
        Behaviors.same
      }.receiveSignal {
        case (ctx, ChildFailed(ref, cause)) =>
          ctx.log.error(s"Child actor ${ref.path} failed with error ${cause.getMessage}")
          Behaviors.same
      }
    }
  }

  implicit val timeout: Timeout = 5.seconds

  trait Resource
  case class File(id: String, content: Array[Byte], mimeType: String) extends Resource

  object WebServer {

    trait Request
    case class Get(path: String, replyTo: ActorRef[Response]) extends Request

    trait Response {
      val path: String
    }
    case class Ok(path: String, resource: Resource) extends Response
    case class NotFound(path: String) extends Response
    case class BadRequest(path: String) extends Response
    case class InternalServerError(path: String, error: String) extends Response

    case class AdaptedHitResponse(path: String, resource: Resource, replyTo: ActorRef[Response]) extends Request

    case class AdaptedMissResponse(path: String, replyTo: ActorRef[Response]) extends Request

    case class AdaptedErrorResponse(path: String, replyTo: ActorRef[Response], error: String) extends Request

    def apply(): Behavior[Request] = {
      Behaviors.setup { context =>
        val filesystem = context.spawn(Filesystem(), "filesystem")
        val cache = context.spawn(Cache(filesystem), "cache")
        Behaviors.supervise {
          serve(context, cache)
        }.onFailure[IllegalArgumentException](SupervisorStrategy.restart.withStopChildren(false))
      }
    }

    private def serve(context: ActorContext[Request], cache: ActorRef[Cache.Find]): Behavior[Request] =
      Behaviors.receiveMessage { message =>
        message match {
          case Get(path, replyTo) =>
            if (isNotValid(path)) {
              replyTo ! BadRequest(path)
            }
            tryToMakeTheServerRestart(path)
            tryToMakeTheServerStop(path)
            findInCache(cache, context, replyTo, path)
          case AdaptedHitResponse(path, resource, replyTo) =>
            replyTo ! Ok(path, resource)
          case AdaptedMissResponse(path, replyTo) =>
            replyTo ! NotFound(path)
          case AdaptedErrorResponse(path, replyTo, error) =>
            replyTo ! InternalServerError(path, error)
        }
        Behaviors.same
      }

    private def isNotValid(path: String): Boolean =
      Try(new URL(path)).map(_ => false).getOrElse(true)

    def tryToMakeTheServerRestart(path: String): Unit =
      if (path.contains("restart"))
        throw new IllegalArgumentException

    def tryToMakeTheServerStop(path: String): Unit =
      if (path.contains("stop"))
        throw new IllegalStateException

    private def findInCache(cache: ActorRef[Cache.Find],
                            context: ActorContext[Request],
                            replyTo: ActorRef[Response],
                            path: String): Unit = {
      context.ask(cache, (ref: ActorRef[Cache.Response]) => Cache.Find(path, ref)) {
        case Success(Cache.Hit(resource)) => AdaptedHitResponse(path, resource, replyTo)
        case Success(Cache.Miss) => AdaptedMissResponse(path, replyTo)
        case Failure(ex) => AdaptedErrorResponse(path, replyTo, ex.getMessage)
      }
    }
  }

  object Filesystem {

    case class FsFind(path: String, replyTo: ActorRef[FilesystemResponse])

    trait FilesystemResponse
    case class FsFound(resource: Resource) extends FilesystemResponse
    case object FsMiss extends FilesystemResponse

    def apply(): Behavior[FsFind] = {
      Behaviors
        .supervise[FsFind](
          Behaviors
            .supervise(search)
            .onFailure[IOException](SupervisorStrategy.resume))
        .onFailure[Exception](SupervisorStrategy.restart.withLimit(
          maxNrOfRetries = 10, withinTimeRange = 5 minutes))
    }

    private def search: Behavior[FsFind] =
      Behaviors.receive[FsFind] { (context, message) =>
        context.log.info(s"Received a request for path ${message.path}")
        val response = if (message.path.contains("valid"))
          FsFound(File("id", "{'result': 'ok'}".getBytes(), "application/json"))
        else
          FsMiss
        message.replyTo ! response
        Behaviors.same
      }.receiveSignal {
        case (ctx, signal) if signal == PreRestart || signal == PostStop =>
          ctx.log.info("Releasing any dangling resource")
          Behaviors.same
      }
  }

  object Cache {

    trait Response

    trait Request

    case class Find(path: String, replyTo: ActorRef[Response]) extends Request

    case class AdaptedFsFound(path: String, resource: Resource, replyTo: ActorRef[Response]) extends Request

    case class AdaptedFsMiss(path: String, replyTo: ActorRef[Response]) extends Request

    case class Hit(resource: Resource) extends Response

    case object Miss extends Response

    def apply(filesystem: ActorRef[FsFind]): Behavior[Request] =
      Behaviors
        .supervise(cache(filesystem, Map[String, Resource]()))
        .onFailure(SupervisorStrategy.resume)

    def cache(filesystem: ActorRef[FsFind],
              cacheMap: Map[String, Resource]): Behavior[Request] =
      Behaviors.receive { (ctx, message) =>
        message match {
          case Find(path, replyTo) =>
            if (path.contains("resume"))
              throw new RuntimeException
            val maybeAnHit = cacheMap.get(path)
            maybeAnHit match {
              case Some(r) => replyTo ! Hit(r)
              case None => askFilesystemForResource(filesystem, ctx, path, replyTo)
            }
            Behaviors.same
          case AdaptedFsFound(path, res, replyTo) =>
            replyTo ! Hit(res)
            cache(filesystem, cacheMap + (path -> res))
          case AdaptedFsMiss(_, replyTo) =>
            replyTo ! Miss
            Behaviors.same
        }
      }

    private def askFilesystemForResource(filesystem: ActorRef[FsFind],
                                         ctx: ActorContext[Request],
                                         path: String,
                                         replyTo: ActorRef[Response]): Unit = {
      ctx.ask(filesystem, (ref: ActorRef[Filesystem.FilesystemResponse]) => FsFind(path, ref)) {
        case Success(FsFound(resource)) => AdaptedFsFound(path, resource, replyTo)
        case _ => AdaptedFsMiss(path, replyTo)
      }
    }
  }

}
