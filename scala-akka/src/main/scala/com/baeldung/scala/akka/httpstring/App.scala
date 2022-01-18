package com.baeldung.scala.akka.httpstring

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.unmarshalling.Unmarshal

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContextExecutor}

object App {
  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[Nothing] =
      ActorSystem(Behaviors.empty, "simple-system")
    implicit val executionContext: ExecutionContextExecutor =
      system.executionContext

    val binding = Http().newServerAt("localhost", 8080).bind(SimpleRouter.route)

    val responseFuture =
      Http().singleRequest(HttpRequest(uri = "http://localhost:8080/hello"))
    val timeout = 300.millis
    val responseAsString = Await.result(
      responseFuture
        .flatMap { resp => resp.entity.toStrict(timeout) }
        .map { strictEntity => strictEntity.data.utf8String },
      timeout
    )

    assert(responseAsString == "Hello, world!")

    val responseAsString2 = Await.result(
      responseFuture.flatMap(resp => Unmarshal(resp.entity).to[String]),
      timeout
    )

    assert(responseAsString2 == "Hello, world!")

    binding
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}
