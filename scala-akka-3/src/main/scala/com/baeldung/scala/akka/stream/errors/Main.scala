package com.baeldung.scala.akka.stream.errors

import akka.actor.ActorSystem
import akka.stream.RestartSettings
import akka.stream.scaladsl.RestartSource

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps
import scala.util.{Failure, Success}

object Main extends App {
  implicit val system: ActorSystem = ActorSystem("baeldung")

  private val backoffSettings = RestartSettings(
    minBackoff = 3 seconds,
    maxBackoff = 30 seconds,
    randomFactor = 0.2
  ).withMaxRestarts(3, 5.minutes)

  RestartSource
    .withBackoff(backoffSettings) { () => source }
    .via(parse)
    .via(compare)
    .runWith(sink)
    .andThen {
      case Failure(exception) => println(exception)
      case Success((correct, total)) =>
        println(s"$correct/$total correct answers")
    }(system.dispatcher)
    .onComplete(_ => system.terminate())(system.dispatcher)

  /*source
    .via(parseWithLogging)
    .via(compare)
    .runWith(sink)
    .andThen {
      case Failure(exception) => println(exception)
      case Success((correct, total)) =>
        println(s"$correct/$total correct answers")
    }(system.dispatcher)
    .onComplete(_ => system.terminate())(system.dispatcher)*/
}
