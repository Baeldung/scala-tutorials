package com.baeldung.scala.akka.httpstring

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

object SimpleRouter {
  val route: Route = path("hello") {
    get {
      complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "Hello, world!"))
    }
  }
}
