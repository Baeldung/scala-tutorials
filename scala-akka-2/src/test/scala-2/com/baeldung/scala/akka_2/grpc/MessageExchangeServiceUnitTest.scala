package com.baeldung.scala.akka_2.grpc

import akka.actor.ActorSystem
import akka.stream.scaladsl.{Keep, Sink, Source}
import akka.testkit.TestKit
import org.scalatest.BeforeAndAfterAll
import org.scalatest.wordspec.AsyncWordSpecLike

class MessageExchangeServiceUnitTest
  extends TestKit(ActorSystem("Akka-gRPC"))
  with AsyncWordSpecLike
  with BeforeAndAfterAll {

  override def beforeAll(): Unit =
    new MessageExchangeServer().startServer

  override def afterAll(): Unit =
    TestKit.shutdownActorSystem(system)

  "messageExchangeService" should {

    "send single request and receive single response" in {
      val messageExchangeClient = new MessageExchangeClient()

      messageExchangeClient
        .sendSingleMessage("single message")
        .map(responseMessage => {
          assert(
            responseMessage.responseMessage == s"Responding to single message"
          )
        })
    }

    "send single request and receive stream response" in {
      val messageExchangeClient = new MessageExchangeClient()

      messageExchangeClient
        .sendSingleMessageStreamResponse("single message")
        .toMat(Sink.seq)(Keep.right)
        .run()
        .map(responseMessages => {
          assert(responseMessages.size == 1)
          assert(responseMessages.head.timestamp.nonEmpty)
          assert(
            responseMessages.head.responseMessage == "Stream responding to single message"
          )
        })
    }

    "stream requests and receive single response" in {
      val messageExchangeClient = new MessageExchangeClient()

      messageExchangeClient
        .streamMessagesSingleResponse(
          Source(List("message 1", "message 2", "message 3"))
        )
        .map(responseMessage => {
          assert(responseMessage.timestamp.nonEmpty)
          assert(
            responseMessage.responseMessage == "Responding to stream message 1, message 2, message 3"
          )
        })
    }

    "stream requests and stream responses" in {
      val messageExchangeClient = new MessageExchangeClient()

      messageExchangeClient
        .streamMessages(
          Source(List("message 1", "message 2", "message 3"))
        )
        .toMat(Sink.seq)(Keep.right)
        .run()
        .map(responseMessages => {
          assert(responseMessages.size == 3)
          assert(responseMessages.flatMap(_.timestamp).size == 3)
          assert(
            responseMessages.head.responseMessage == "Stream responding to message 1"
          )
          assert(
            responseMessages.tail.head.responseMessage == "Stream responding to message 2"
          )
          assert(
            responseMessages.tail.tail.head.responseMessage == "Stream responding to message 3"
          )
        })
    }
  }
}
