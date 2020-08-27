package com.baeldung.scala.akka.requestresponse

import java.nio.charset.StandardCharsets
import java.util.Base64

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, Behavior}
import com.baeldung.scala.akka.requestresponse.Base64Application.Base64Encoder.{Encode, Encoded, Request}

object Base64Application {

  object Base64Encoder {
    sealed trait Request
    case class Encode(payload: String, replyTo: ActorRef[Encoded]) extends Request
    sealed trait Response
    case class Encoded(payload: String) extends Response

    def apply(): Behavior[Encode] =
      Behaviors.receiveMessage {
        case Encode(payload, replyTo) =>
          val encodedPayload = Base64.getEncoder.encode(payload.getBytes(StandardCharsets.UTF_8))
          replyTo ! Encoded(encodedPayload.toString)
          Behaviors.same
    }
  }

  object NaiveEncoderClient {
    def apply(encoder: ActorRef[Request]): Behavior[Encoded] =
      Behaviors.setup { context =>
        encoder ! Encode("The answer is 42", context.self)
        Behaviors.receiveMessage {
          case Encoded(payload) => context.log.info(s"The encoded payload is $payload")
            Behaviors.empty
        }
      }
  }
}
