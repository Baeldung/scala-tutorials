package com.baeldung.scala.akka

import akka.NotUsed
import akka.stream.scaladsl.{Flow, Sink, Source}

import scala.concurrent.Future

package object stream {
  val source: Source[String, NotUsed] = Source(
    Seq("5,10", "15,15", "78,79", "12,12", "0,0", "456,456")
  )

  val parse: Flow[String, (Int, Int), NotUsed] =
    Flow[String]
      .map { pair =>
        val parts = pair.split(",")
        (parts(0).toInt, parts(1).toInt)
      }

  val compare: Flow[(Int, Int), Boolean, NotUsed] =
    Flow[(Int, Int)]
      .map { case (userAnswer, correctAnswer) => userAnswer == correctAnswer }

  val sink: Sink[Boolean, Future[(Int, Int)]] = Sink.fold((0, 0)) {
    case ((correctCount, total), wasCorrect) =>
      if (wasCorrect) (correctCount + 1, total + 1)
      else (correctCount, total + 1)
  }
}
