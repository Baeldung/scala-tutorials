package com.baeldung.scala.akka.requestresponse

import akka.actor.testkit.typed.CapturedLogEvent
import akka.actor.testkit.typed.scaladsl.{BehaviorTestKit, TestInbox}
import com.baeldung.scala.akka.requestresponse.Base64Application.Base64Encoder.{Encode, Encoded}
import com.baeldung.scala.akka.requestresponse.Base64Application.{Base64Encoder, NaiveEncoderClient}
import org.scalatest.FlatSpec
import org.slf4j.event.Level

class Base64ApplicationUnitTest extends FlatSpec {

  "The Base64Encoder" should "encode a given payload" in {
    val clientInbox = TestInbox[Encoded]()
    val encoder = BehaviorTestKit(Base64Encoder())
    encoder.run(Encode("The answer is 42", clientInbox.ref))
    clientInbox.expectMessage(Encoded("VGhlIGFuc3dlciBpcyA0Mg=="))
  }

  "NaiveEncoderClient" should "log the received encoded message" in {
    val encoder = TestInbox[Encode]()
    val naiveClient = BehaviorTestKit(NaiveEncoderClient(encoder.ref))
    encoder.expectMessage(Encode("The answer is 42", naiveClient.ref))
  }

  it should "receive the encoded message and log it" in {
    val encoder = TestInbox[Encode]()
    val naiveClient = BehaviorTestKit(NaiveEncoderClient(encoder.ref))
    naiveClient.run(Encoded("VGhlIGFuc3dlciBpcyA0Mg=="))
    assertResult(naiveClient.logEntries()) {
      Seq(CapturedLogEvent(Level.INFO, "The encoded payload is VGhlIGFuc3dlciBpcyA0Mg=="))
    }
  }
}
