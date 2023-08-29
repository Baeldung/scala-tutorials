package com.baeldung.scala.akka.stream

import akka.actor.ActorSystem

import scala.util.{Failure, Success}

object Main extends App {
  implicit val system: ActorSystem = ActorSystem("baeldung")

  source
    .via(parse)
    .via(compare)
    .runWith(sink)
    .andThen {
      case Failure(exception) => println(exception)
      case Success((correct, total)) =>
        println(s"$correct/$total correct answers")
    }(system.dispatcher)
    .onComplete(_ => system.terminate())(system.dispatcher)
}
