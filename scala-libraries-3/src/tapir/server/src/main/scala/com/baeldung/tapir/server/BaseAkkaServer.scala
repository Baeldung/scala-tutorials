package com.baeldung.tapir.server

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.{Http, server}
import sttp.tapir.AnyEndpoint
import sttp.tapir.server.akkahttp.AkkaHttpServerInterpreter
import sttp.tapir.swagger.bundle.SwaggerInterpreter

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.io.StdIn

trait BaseAkkaServer {

  val serverPort = 11223
  implicit val system: ActorSystem = ActorSystem("my-system")

  def withSwaggerDocs(endpoints: List[AnyEndpoint]): server.Route = {
    AkkaHttpServerInterpreter().toRoute(SwaggerInterpreter().fromEndpoints[Future](endpoints, "My App", "1.0"))
  }

  def start(routes: Iterable[server.Route]): Unit = {
    val server = Http()
      .newServerAt("localhost", serverPort)
      .bindFlow(
        routes.reduce((r1, r2) => r1 ~ r2)
      )

    println(s"Server now online.")
    println("Press RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    server
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
