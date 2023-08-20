package com.bealdung.scala.zio.testingapplications

import zio.test.ZIOSpecDefault
import zio.test._
import zio.test.Assertion._
import com.baeldung.scala.zio.testingapplications.TestingApplicationsExamples._

object TestingApplicationsExamplesSpec extends ZIOSpecDefault {
  override def spec = suite("TestingApplicationsExamplesSpec")(
    test("returnString correctly returns string") {
      val testString = "Hello World!"
      for {
        output <- returnString(testString)
      } yield assertTrue(output == testString)
    },
    test("using logical operations") {
      val testString = "Hello World!"
      val andAssertion: Assertion[String] =
        Assertion.startsWithString("Hello") && Assertion.endsWithString(
          "World!"
        )

      for {
        output <- returnString(testString)
      } yield assert(output)(andAssertion)
    },
    test("nested assertions") {
      assert(Some(1))(isSome(equalTo(1)))
    },
    test("String assertions") {
      assert("Hello World!")(containsString("Hello")) &&
      assert("Hello World!")(equalsIgnoreCase("HELLO WORLD!")) &&
      assert("Hello World!")(hasSizeString(equalTo(12)))
    },
    test("Numeric assertions") {
      assert(0)(isZero) &&
      assert(1)(isPositive) &&
      assert(-1)(isNegative) &&
      assert(102)(approximatelyEquals(100, 2))
    },
    test("Iterator assertions") {
      assert(List(1, 2, 3))(contains(2)) &&
      assert(List(1, 2, 3))(hasSize(equalTo((3)))) &&
      assert(List(1, 2, 3))(hasNoneOf(List(5, 6)))
    },
    test("Either assertions") {
      assert(Right(1))(isRight) &&
      assert(Left("Oh no"))(isLeft)
    },
    test("Boolean assertions") {
      assert(true)(isTrue) &&
      assert(false)(isFalse)
    },
    test("Option assertions") {
      assert(None)(isNone) &&
      assert(Some(1))(isSome) &&
      assert(Some(1))(isSome(equalTo(1)))
    }
  )
}
