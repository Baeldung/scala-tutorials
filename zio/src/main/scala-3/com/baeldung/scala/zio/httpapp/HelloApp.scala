package com.baeldung.scala.zio.httpapp

import com.baeldung.scala.zio.httpapp.MainApp.Environment
import zio.*
import zio.http.*

object HelloApp extends ZIOAppDefault:
  private object HelloHttpApp:
    def apply(): Http[Any, Nothing, Request, Response] =
      Http.collect[Request] { case Method.GET -> Root / "hello" =>
        Response.text(s"Hello World!")
      }

  def run: ZIO[Environment with ZIOAppArgs with Scope, Throwable, Any] =
    Server
      .serve(HelloHttpApp())
      .provide(
        Server.defaultWithPort(8080)
      )
