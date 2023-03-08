package com.baeldung.scala.akka_2.query_parameters

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object QueryParameters extends App {
  implicit val system: ActorSystem[Nothing] =
    ActorSystem(Behaviors.empty, "my-system")
  implicit val executionContext: ExecutionContextExecutor =
    system.executionContext

  val route =
    pathPrefix("params") {
      concat(
        path("with-parameters") {
          get {
            parameters(Symbol("page").as[String], Symbol("size").as[String]) {
              (page, size) =>
                complete(
                  HttpEntity(
                    ContentTypes.`text/plain(UTF-8)`,
                    s"parameters passed: page=$page and size=$size"
                  )
                )
            }
          }
        },
        path("with-extract") {
          get {
            extract(_.request.uri.query()) { params =>
              complete(
                HttpEntity(
                  ContentTypes.`text/plain(UTF-8)`,
                  s"parameters passed: page=${params.get("page").get} and size=${params.get("size").get}"
                )
              )
            }
          }
        }
      )
    }

  val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)

  println(
    s"Server now online. Please navigate to http://localhost:8080/params\nPress RETURN to stop..."
  )

  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())
}
