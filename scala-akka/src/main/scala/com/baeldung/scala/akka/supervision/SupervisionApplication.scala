package com.baeldung.scala.akka.supervision

import java.io.IOException
import java.net.URL

import akka.actor.typed._
import akka.actor.typed.scaladsl.{ActorContext, Behaviors}
import akka.util.Timeout
import com.baeldung.scala.akka.supervision.SupervisionApplication.Filesystem.{FsFind, FsFound}

import scala.concurrent.duration.DurationInt
import scala.util.{Failure, Random, Success, Try}

object SupervisionApplication {

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
        }.onFailure[Exception](SupervisorStrategy.restart.withStopChildren(false))
      }
    }

    private def serve(context: ActorContext[Request], cache: ActorRef[Cache.Find]): Behavior[Request] =
      Behaviors.receiveMessage { message =>
        message match {
          case Get(path, replyTo) =>
            if (isNotValid(path)) {
              replyTo ! BadRequest(path)
            }
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
      Try(new URL(path)).map(_ => true).getOrElse(false)

    private def findInCache(cache: ActorRef[Cache.Find],
                            context: ActorContext[Request],
                            replyTo: ActorRef[Response],
                            path: String): Unit = {
      context.ask(cache, ref => Cache.Find(path, ref)) {
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
        message.replyTo !
        if (Random.nextBoolean)
          FsFound(File("id", "{'result': 'ok'}".getBytes(), "application/json"))
        else
          FsMiss
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
            val maybeAnHit = cacheMap.get(path)
            maybeAnHit match {
              case Some(r) => replyTo ! Hit(r)
              case None =>
                ctx.ask(filesystem, ref => FsFind(path, ref)) {
                  case Success(FsFound(resource)) => AdaptedFsFound(path, resource, replyTo)
                  case _ => AdaptedFsMiss(path, replyTo)
                }
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
  }

}
