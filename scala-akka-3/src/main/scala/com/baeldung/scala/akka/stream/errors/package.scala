package com.baeldung.scala.akka.stream

import akka.NotUsed
import akka.stream.Attributes
import akka.stream.scaladsl.{Flow, Sink, Source}

import scala.concurrent.Future

package object errors {
  val source: Source[String, NotUsed] = Source(
    Seq("5,10", "15,15", "78,79", "12,12", "0,0", "456,456")
  )

  val parse: Flow[String, (Int, Int), NotUsed] =
    Flow[String]
      .map { pair =>
        val parts = pair.split(",")
        (parts(0).toInt, parts(1).toInt)
      }

  val parseWithRecover: Flow[String, Either[String, (Int, Int)], NotUsed] =
    Flow[String]
      .map { pair =>
        val parts = pair.split(",")
        Right((parts(0).toInt, parts(1).toInt))
      }
      .recover({ case e: ArrayIndexOutOfBoundsException =>
        Left(e.getMessage)
      })

  val parseWithLogging: Flow[String, (Int, Int), NotUsed] =
    Flow[String]
      .map { pair =>
        val parts = pair.split(",")
        (parts(0).toInt, parts(1).toInt)
      }
      .log(name = "Baeldung stream")
      .addAttributes(
        Attributes.logLevels(
          onElement = Attributes.LogLevels.Info
        )
      )

  val compare: Flow[(Int, Int), Boolean, NotUsed] =
    Flow[(Int, Int)]
      .map { case (userAnswer, correctAnswer) => userAnswer == correctAnswer }

  val sink: Sink[Boolean, Future[(Int, Int)]] = Sink.fold((0, 0)) {
    case ((correctCount, total), wasCorrect) =>
      if (wasCorrect) (correctCount + 1, total + 1)
      else (correctCount, total + 1)
  }
}
