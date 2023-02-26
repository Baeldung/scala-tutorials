package com.baeldung.scala.akka.http.routetesting

import akka.http.scaladsl.server._
import Directives._
import akka.http.scaladsl.model.StatusCodes

object AkkaRoutes {
  val routes =
    get {
      concat(
        path("hello") {
          complete("Hello World!")
        },
        path("hello-name") {
          parameters(Symbol("name").as[String]) {
            name => {
              complete(s"Hello $name")
            }
          }
        },
        path("hello-user") {
          headerValueByName("X-User-Id") { userId =>
            if(userId== "123") {
              complete("Welcome user")
            } else {
              complete(StatusCodes.Unauthorized, "Incorrect user")
            }
          }
        }
      )
    } ~
      post {
        path("hello-name") {
          entity(as[String]) { name =>
            complete(s"Hello $name")
          }
        }
      }
}
