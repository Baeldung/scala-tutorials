package com.baeldung.scala.akka_2.grpc
import akka.NotUsed
import akka.stream.Materializer
import akka.stream.scaladsl.{Sink, Source}
import com.google.protobuf.timestamp.Timestamp

import java.time.Instant
import java.util.UUID
import scala.concurrent.Future

class MessageExchangeServiceImpl(implicit val mat: Materializer)
  extends MessageExchangeService {

  import mat.executionContext

  override def sendMessage(
    receivingMessage: MessageRequest
  ): Future[MessageResponse] = {
    val response = MessageResponse(
      id = UUID.randomUUID().toString,
      responseMessage = s"Responding to ${receivingMessage.message}",
      timestamp = Some(getTimestamp),
      extraInfo = receivingMessage.extraInfo
    )
    Future.successful(response)
  }

  override def streamMessagesSingleResponse(
    receivingMessageStream: Source[MessageRequest, NotUsed]
  ): Future[MessageResponse] =
    receivingMessageStream
      .runWith(Sink.seq)
      .map(messages =>
        MessageResponse(
          id = UUID.randomUUID().toString,
          responseMessage =
            s"Responding to stream ${messages.map(_.message).mkString(", ")}",
          timestamp = Some(getTimestamp),
          extraInfo = Seq.empty
        )
      )

  override def sendMessageStreamResponse(
    receivingMessage: MessageRequest
  ): Source[MessageResponse, NotUsed] =
    Source(
      List(
        MessageResponse(
          id = UUID.randomUUID().toString,
          responseMessage = s"Stream responding to ${receivingMessage.message}",
          timestamp = Some(getTimestamp),
          extraInfo = receivingMessage.extraInfo
        )
      )
    )

  override def streamMessages(
    receivingMessageStream: Source[MessageRequest, NotUsed]
  ): Source[MessageResponse, NotUsed] =
    receivingMessageStream.map(receivingMessage =>
      MessageResponse(
        id = UUID.randomUUID().toString,
        responseMessage = s"Stream responding to ${receivingMessage.message}",
        timestamp = Some(getTimestamp),
        extraInfo = receivingMessage.extraInfo
      )
    )

  private def getTimestamp: Timestamp = {
    val time = Instant.now()
    Timestamp.of(time.getEpochSecond, time.getNano)
  }
}
