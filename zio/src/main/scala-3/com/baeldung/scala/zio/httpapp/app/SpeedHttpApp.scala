package com.baeldung.scala.zio.httpapp.app

import zio.{Ref, ZIO}
import zio.http._

object SpeedHttpApp:

  val appContext = "speed"

  def apply(): Http[Ref[Int], Nothing, Request, Response] =
    Http.collectZIO[Request] {
      case Method.GET -> Root / SpeedHttpApp.appContext / "add" =>
        ZIO.serviceWithZIO[Ref[Int]] { ref =>
          ref
            .updateAndGet(_ + 1)
            .map(_.toString)
            .map(Response.text)
        }
      case Method.GET -> Root / SpeedHttpApp.appContext / "reduce" =>
        ZIO.serviceWithZIO[Ref[Int]] { ref =>
          ref
            .updateAndGet(_ - 1)
            .map(_.toString)
            .map(Response.text)
        }
      case Method.GET -> Root / SpeedHttpApp.appContext / "get" =>
        ZIO.serviceWithZIO[Ref[Int]](ref =>
          ref.get.map(_.toString).map(Response.text)
        )
    }
