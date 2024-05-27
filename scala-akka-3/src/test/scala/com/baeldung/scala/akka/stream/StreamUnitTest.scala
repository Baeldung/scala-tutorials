package com.baeldung.scala.akka.stream

import akka.actor.ActorSystem
import akka.stream.scaladsl.Keep
import akka.stream.testkit.scaladsl.{TestSink, TestSource}
import org.scalatest.concurrent.ScalaFutures.convertScalaFuture
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class StreamUnitTest extends AnyFlatSpec with Matchers {
  implicit val system: ActorSystem = ActorSystem("baeldung")

  "The \"parse\" flow" should "parse pairs of integers" in {
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

  "The \"parse\" flow" should "reject pairs with the wrong delimiter" in {
    val (pub, sub) = TestSource[String]()
      .via(parse)
      .toMat(TestSink[(Int, Int)]())(Keep.both)
      .run()

    pub.sendNext("2,3")
    pub.sendNext("1;1")
    pub.sendComplete()

    sub.request(2)
    sub.expectNext((2, 3))
    val exc = sub.expectError()
    exc.getMessage should be("For input string: \"1;1\"")
  }

  "The \"parse\" flow" should "reject pairs with more than two numbers" in {
    val (pub, sub) = TestSource[String]()
      .via(parse)
      .toMat(TestSink[(Int, Int)]())(Keep.both)
      .run()

    pub.sendNext("(1,3),(4,5)")
    pub.sendComplete()

    val exc = sub.expectSubscriptionAndError()
    exc.getMessage should be("For input string: \"(1\"")
  }

  "The \"parse\" flow" should "reject pairs with only one number" in {
    val (pub, sub) = TestSource[String]()
      .via(parse)
      .toMat(TestSink[(Int, Int)]())(Keep.both)
      .run()

    pub.sendNext("1")
    pub.sendComplete()

    val exc = sub.expectSubscriptionAndError()
    exc.getMessage should be("Index 1 out of bounds for length 1")
  }

  "The \"parse\" flow" should "reject pairs with the wrong types" in {
    val (pub, sub) = TestSource[String]()
      .via(parse)
      .toMat(TestSink[(Int, Int)]())(Keep.both)
      .run()

    pub.sendNext("2,3")
    pub.sendNext("1,a")
    pub.sendComplete()

    sub.request(2)
    sub.expectNext((2, 3))
    val exc = sub.expectError()
    exc.getMessage should be("For input string: \"a\"")
  }

  "The \"compare\" flow" should "compare pairs of integers" in {
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

  "The \"sink\" sink" should "count the number of trues" in {
    val (probe, result) = TestSource[Boolean]().toMat(sink)(Keep.both).run()

    probe.sendNext(true)
    probe.sendNext(false)
    probe.sendNext(false)
    probe.sendComplete()

    result.futureValue shouldBe (1, 3)
  }

  "The \"source\" source" should "emit pairs of integers" in {
    source
      .runWith(TestSink[String]())
      .request(6)
      .expectNext("5,10", "15,15", "78,79", "12,12", "0,0", "456,456")
      .expectComplete()
  }
}
