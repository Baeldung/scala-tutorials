package com.baeldung.scala.http4s

import cats.effect.unsafe.implicits.global
import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.blaze.client.BlazeClientBuilder
import org.http4s.client.Client
import org.http4s.implicits.uri

object SimpleClient extends IOApp {
  def callEffect(client: Client[IO], str: String): IO[String] =
    client.expect[String](uri"http://localhost:8080/length/" / str)

  override def run(args: List[String]): IO[ExitCode] =
    BlazeClientBuilder[IO].resource
      .use { client =>
        println(callEffect(client, "Baeldung").unsafeRunSync())
        IO.unit
      }
      .as(ExitCode.Success)
}
