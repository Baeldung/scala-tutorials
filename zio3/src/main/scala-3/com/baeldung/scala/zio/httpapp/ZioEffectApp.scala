package com.baeldung.scala.zio.httpapp

import com.baeldung.scala.zio.httpapp.HelloApp.Environment
import zio.*
import zio.http.*

object ZioEffectApp extends ZIOAppDefault:
  private object CounterHttpApp:
    def apply(): Http[Ref[Int], Nothing, Request, Response] =
      Http.collectZIO[Request] {
        case Method.GET -> Root / "up" =>
          ZIO.serviceWithZIO[Ref[Int]](cRef =>
            response(cRef.updateAndGet(_ + 1))
          )
        case Method.GET -> Root / "get" =>
          ZIO.serviceWithZIO[Ref[Int]](cRef => response(cRef.get))
        case Method.GET -> Root / "reset" =>
          ZIO.serviceWithZIO[Ref[Int]](cRef =>
            response(cRef.updateAndGet(_ => 0))
          )
      }

    private def response(counterUio: UIO[Int]) = counterUio
      .map(_.toString)
      .map(Response.text)

  def run: ZIO[Environment with ZIOAppArgs with Scope, Throwable, Any] =
    Server
      .serve(CounterHttpApp())
      .provide(
        ZLayer.fromZIO(Ref.make(0)),
        Server.defaultWithPort(8080)
      )
