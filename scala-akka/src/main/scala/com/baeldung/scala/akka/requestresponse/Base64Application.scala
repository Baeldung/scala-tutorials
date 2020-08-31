package com.baeldung.scala.akka.requestresponse

import java.nio.charset.StandardCharsets
import java.util.Base64

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, Behavior}
import akka.util.Timeout
import com.baeldung.scala.akka.requestresponse.Base64Application.Base64Encoder.{Encode, Encoded, Request}

import scala.concurrent.duration.DurationInt
import scala.util.{Failure, Success}

object Base64Application {

  object Base64Encoder {
    sealed trait Request
    final case class Encode(payload: String, replyTo: ActorRef[Encoded]) extends Request
    sealed trait Response
    final case class Encoded(payload: String) extends Response

    def apply(): Behavior[Encode] =
      Behaviors.receiveMessage {
        case Encode(payload, replyTo) =>
          val encodedPayload = Base64.getEncoder.encode(payload.getBytes(StandardCharsets.UTF_8))
          replyTo ! Encoded(encodedPayload.map(_.toChar).mkString)
          Behaviors.same
    }
  }

  object NaiveEncoderClient {
    def apply(encoder: ActorRef[Encode]): Behavior[Encoded] =
      Behaviors.setup { context =>
        encoder ! Encode("The answer is 42", context.self)
        Behaviors.receiveMessage {
          case Encoded(payload) => context.log.info(s"The encoded payload is $payload")
            Behaviors.empty
        }
      }
  }

  object EncoderClient {
    sealed trait Command
    final case class KeepASecret(secret: String) extends Command
    private final case class WrappedEncoderResponse(response: Encoded) extends Command

    def apply(encoder: ActorRef[Request]): Behavior[Command] =
      Behaviors.setup { context =>
        val encoderResponseMapper: ActorRef[Encoded] =
          context.messageAdapter(response => WrappedEncoderResponse(response))
        Behaviors.receiveMessage {
          case KeepASecret(secret) =>
            encoder ! Encode(secret, encoderResponseMapper)
            Behaviors.same
          case WrappedEncoderResponse(response) =>
            context.log.info(s"I will keep a secret for you: ${response.payload}")
            Behaviors.same
        }
      }
  }

  object APIGateway {
    sealed trait Command
    final case class PleaseEncode(payload: String, replyTo: ActorRef[GentlyEncoded]) extends Command
    final case class GentlyEncoded(encodedPayload: String)
    private case class AdaptedResponse(payload: String, replyTo: ActorRef[GentlyEncoded]) extends Command
    private case class AdaptedErrorResponse(error: String) extends Command

    def apply(encoder: ActorRef[Request]): Behavior[Command] =
      Behaviors.setup { context =>
        implicit val timeout: Timeout = 5.seconds
        Behaviors.receiveMessage {
          case PleaseEncode(payload, replyTo) =>
            context.ask(encoder, (ref: ActorRef[Encoded]) => Encode(payload, ref)) {
              case Success(Encoded(encodedPayload)) => AdaptedResponse(encodedPayload, replyTo)
              case Failure(exception) => AdaptedErrorResponse(exception.getMessage)
            }
            Behaviors.same
          case AdaptedResponse(encoded, ref) =>
            ref ! GentlyEncoded(encoded)
            Behaviors.same
          case AdaptedErrorResponse(error) =>
            context.log.error(s"There was an error during encoding: $error")
            Behaviors.same
        }
      }
  }
}
