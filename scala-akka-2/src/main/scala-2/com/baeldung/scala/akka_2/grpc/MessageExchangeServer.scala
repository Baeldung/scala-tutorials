package com.baeldung.scala.akka_2.grpc

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}

import scala.concurrent.{ExecutionContextExecutor, Future}

class MessageExchangeServer()(implicit actorSystem: ActorSystem) {
  implicit val executionContext: ExecutionContextExecutor =
    actorSystem.dispatcher

  val service: HttpRequest => Future[HttpResponse] =
    MessageExchangeServiceHandler(new MessageExchangeServiceImpl())

  def startServer: Future[Http.ServerBinding] =
    Http().newServerAt("127.0.0.1", 8090).bind(service)
}
