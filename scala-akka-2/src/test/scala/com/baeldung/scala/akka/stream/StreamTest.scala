package com.baeldung.scala.akka.stream

import akka.actor.ActorSystem
import akka.stream.scaladsl.Keep
import akka.stream.testkit.scaladsl.{TestSink, TestSource}
import org.scalatest.concurrent.ScalaFutures.convertScalaFuture
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class StreamTest extends AnyFlatSpec with Matchers {
  implicit val system: ActorSystem = ActorSystem("baeldung")

  "The parse flow" should "parse pairs of integers" in {
    val (pub, sub) = TestSource[String]()
      .via(parse)
      .toMat(TestSink[(Int, Int)]())(Keep.both)
      .run()

    pub.sendNext("1,1")
    pub.sendNext("145,146")
    pub.sendComplete()

    sub.requestNext((1, 1))
    sub.requestNext((145, 146))
    sub.expectComplete()
  }

  "The compare flow" should "compare pairs of integers" in {
    val (pub, sub) = TestSource[(Int, Int)]()
      .via(compare)
      .toMat(TestSink[Boolean]())(Keep.both)
      .run()

    pub.sendNext((1, 1))
    pub.sendNext((145, 146))
    pub.sendComplete()

    sub.requestNext(true)
    sub.requestNext(false)
    sub.expectComplete()
  }

  "The sink sink" should "count the number of trues" in {
    val (probe, result) = TestSource[Boolean]().toMat(sink)(Keep.both).run()

    probe.sendNext(true)
    probe.sendNext(false)
    probe.sendNext(false)
    probe.sendComplete()

    result.futureValue shouldBe (1, 3)
  }
}
