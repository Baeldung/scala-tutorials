package com.baeldung.scala.akka_2.grpc

import akka.NotUsed
import akka.actor.ActorSystem
import akka.grpc.GrpcClientSettings
import akka.stream.scaladsl.Source

import java.util.UUID
import scala.concurrent.{ExecutionContextExecutor, Future}

class MessageExchangeClient(implicit actorSystem: ActorSystem) {

  implicit val executionContext: ExecutionContextExecutor =
    actorSystem.dispatcher

  private val clientSettings: GrpcClientSettings =
    GrpcClientSettings.fromConfig(MessageExchangeService.name)

  private val client = MessageExchangeServiceClient(clientSettings)

  def sendSingleMessage(message: String): Future[MessageResponse] =
    client.sendMessage(
      MessageRequest(
        id = UUID.randomUUID().toString,
        message = message,
        timestamp = None,
        extraInfo = Seq.empty
      )
    )

  def sendSingleMessageStreamResponse(
    message: String
  ): Source[MessageResponse, NotUsed] =
    client.sendMessageStreamResponse(
      MessageRequest(
        id = UUID.randomUUID().toString,
        message = message,
        timestamp = None,
        extraInfo = Seq.empty
      )
    )

  def streamMessagesSingleResponse(
    messages: Source[String, NotUsed]
  ): Future[MessageResponse] =
    client.streamMessagesSingleResponse(
      messages.map(m =>
        MessageRequest(
          id = UUID.randomUUID().toString,
          message = m,
          timestamp = None,
          extraInfo = Seq.empty
        )
      )
    )

  def streamMessages(
    messages: Source[String, NotUsed]
  ): Source[MessageResponse, NotUsed] =
    client.streamMessages(
      messages.map(m =>
        MessageRequest(
          id = UUID.randomUUID().toString,
          message = m,
          timestamp = None,
          extraInfo = Seq.empty
        )
      )
    )
}
