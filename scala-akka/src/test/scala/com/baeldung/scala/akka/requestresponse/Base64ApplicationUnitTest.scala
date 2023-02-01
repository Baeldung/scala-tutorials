package com.baeldung.scala.akka.requestresponse

import akka.actor.testkit.typed.CapturedLogEvent
import akka.actor.testkit.typed.Effect.MessageAdapter
import akka.actor.testkit.typed.scaladsl.{
  ActorTestKit,
  BehaviorTestKit,
  TestInbox
}
import com.baeldung.scala.akka.requestresponse.Base64Application.APIGateway.{
  GentlyEncoded,
  PleaseEncode
}
import com.baeldung.scala.akka.requestresponse.Base64Application.Base64Encoder.{
  Encoded,
  ToEncode
}
import com.baeldung.scala.akka.requestresponse.Base64Application.EncoderClient.{
  KeepASecret,
  WrappedEncoderResponse
}
import com.baeldung.scala.akka.requestresponse.Base64Application.{
  APIGateway,
  Base64Encoder,
  EncoderClient,
  NaiveEncoderClient
}
import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpec
import org.slf4j.event.Level

class Base64ApplicationUnitTest extends AnyFlatSpec with BeforeAndAfterAll {

  val testKit: ActorTestKit = ActorTestKit()

  "The Base64Encoder" should "encode a given payload" in {
    val clientInbox = TestInbox[Encoded]()
    val encoder = BehaviorTestKit(Base64Encoder())
    encoder.run(ToEncode("The answer is 42", clientInbox.ref))
    clientInbox.expectMessage(Encoded("VGhlIGFuc3dlciBpcyA0Mg=="))
  }

  "NaiveEncoderClient" should "send an encoding request to the encode" in {
    val encoder = TestInbox[ToEncode]()
    val naiveClient = BehaviorTestKit(NaiveEncoderClient(encoder.ref))
    encoder.expectMessage(ToEncode("The answer is 42", naiveClient.ref))
  }

  it should "receive the encoded message and log it" in {
    val encoder = TestInbox[ToEncode]()
    val naiveClient = BehaviorTestKit(NaiveEncoderClient(encoder.ref))
    naiveClient.run(Encoded("VGhlIGFuc3dlciBpcyA0Mg=="))
    assertResult(naiveClient.logEntries()) {
      Seq(
        CapturedLogEvent(
          Level.INFO,
          "The encoded payload is VGhlIGFuc3dlciBpcyA0Mg=="
        )
      )
    }
  }

  "EncoderClient" should "send an encoding request to the encode" in {
    val encoder = TestInbox[ToEncode]()
    val client = BehaviorTestKit(EncoderClient(encoder.ref))
    client.run(KeepASecret("My secret"))
    client.expectEffectPF {
      case MessageAdapter(clazz, _) if clazz == classOf[Encoded] =>
    }
    assertResult(encoder.receiveMessage().payload) {
      "My secret"
    }
  }

  it should "receive the encoded message and log it" in {
    val encoder = TestInbox[ToEncode]()
    val client = BehaviorTestKit(EncoderClient(encoder.ref))
    client.run(WrappedEncoderResponse(Encoded("VGhlIGFuc3dlciBpcyA0Mg==")))
    assertResult(client.logEntries()) {
      Seq(
        CapturedLogEvent(
          Level.INFO,
          "I will keep a secret for you: VGhlIGFuc3dlciBpcyA0Mg=="
        )
      )
    }
  }

  "APIGateway" should "send an encoding request to the encode" in {
    // We need to use the asynchronous test kit, because we need a scheduler
    // to use the ask pattern
    val encoder = testKit.createTestProbe[ToEncode]()
    val client = testKit.createTestProbe[GentlyEncoded]()
    val apiGateway = testKit.spawn(APIGateway(encoder.ref))
    apiGateway ! PleaseEncode("My secret", client.ref)
    assertResult(encoder.receiveMessage().payload) { "My secret" }
  }

  it should "send the encoded message to the client" in {
    val client = testKit.createTestProbe[GentlyEncoded]()
    val encoder = testKit.spawn(Base64Encoder())
    val apiGateway = testKit.spawn(APIGateway(encoder.ref))
    apiGateway ! PleaseEncode("My secret", client.ref)
    client.expectMessage(GentlyEncoded("TXkgc2VjcmV0"))
  }

  override protected def afterAll(): Unit = testKit.shutdownTestKit()
}
