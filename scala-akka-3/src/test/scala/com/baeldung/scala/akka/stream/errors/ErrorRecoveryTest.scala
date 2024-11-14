package com.baeldung.scala.akka.stream.errors

import akka.actor.ActorSystem
import akka.stream.{ActorAttributes, Supervision}
import akka.stream.scaladsl.Keep
import akka.stream.testkit.scaladsl.{TestSink, TestSource}
import org.scalatest.concurrent.ScalaFutures.convertScalaFuture
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ErrorRecoveryTest extends AnyFlatSpec with Matchers {
  implicit val system: ActorSystem = ActorSystem("baeldung")

  "The \"parseWithRecover\" flow" should "parse recover from a parsing error" in {
    val (pub, sub) = TestSource[String]()
      .via(parseWithRecover)
      .toMat(TestSink[Either[String, (Int, Int)]]())(Keep.both)
      .run()

    pub.sendNext("1,1")
    pub.sendNext("145146")
    pub.sendComplete()

    sub.requestNext(Right(1, 1))
    sub.requestNext(Left("Index 1 out of bounds for length 1"))
    sub.expectComplete()
  }

  "The \"Resume\" supervision strategy" should "ignore parsing errors" in {
    val runnableGraph = TestSource[String]()
      .via(parseWithRecover)
      .toMat(TestSink[Either[String, (Int, Int)]]())(Keep.both)

    val decider: Supervision.Decider = {
      case _: ArrayIndexOutOfBoundsException => Supervision.Resume
      case _                                 => Supervision.Stop
    }

    val graphWithResumeSupervision =
      runnableGraph.withAttributes(ActorAttributes.supervisionStrategy(decider))

    val (pub, sub) = graphWithResumeSupervision.run()

    pub.sendNext("1,1")
    pub.sendNext("145146")
    pub.sendNext("1,2")
    pub.sendComplete()

    sub.requestNext(Right(1, 1))
    sub.requestNext(Right(1, 2))
    sub.expectComplete()
  }
}
