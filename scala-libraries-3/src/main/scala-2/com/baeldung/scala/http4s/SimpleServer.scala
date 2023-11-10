package com.baeldung.scala.http4s

import cats.data.Kleisli
import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.dsl.io._
import org.http4s.implicits.http4sKleisliResponseSyntaxOptionT
import org.http4s.server.Router
import org.http4s.{HttpRoutes, Request, Response}

object SimpleServer extends IOApp {
  val route: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "length" / str => Ok(str.length.toString)
  }

  val app: Kleisli[IO, Request[IO], Response[IO]] = Router(
    "/" -> route
  ).orNotFound

  override def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO]
      .bindHttp(8080, "localhost")
      .withHttpApp(app)
      .resource
      .useForever
      .as(ExitCode.Success)
}
