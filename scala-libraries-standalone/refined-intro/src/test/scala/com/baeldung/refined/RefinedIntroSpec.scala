package com.baeldung.refined

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._
import eu.timepit.refined.string._
import eu.timepit.refined.collection._
import eu.timepit.refined.char._
import eu.timepit.refined.boolean._
import eu.timepit.refined.api.Validate
import scala.util.Left

class RefinedProjectSpec extends AnyFlatSpec with Matchers {
  import RefinedIntro.{size => sizeTest, _}

  "Int checks" should "pass with refined types" in {
    assert(refineV[Odd](8) == Left("Predicate (8 % 2 == 0) did not fail."))
    age shouldBe an[Int Refined Less[35]]
    ageInterval shouldBe an[Int Refined Interval.Closed[30, 35]]
    age2 shouldBe an[Int Refined GreaterEqual[35]]
    ageInput shouldBe an[Int]
    ageCheck shouldBe an[Either[String,
      Refined[Int, GreaterEqual[35]]
    ]]
  }

  "Char checks" should "pass for refined types" in {
    assert(myDigit.isInstanceOf[Char Refined Digit])
    assert(myLetter.isInstanceOf[Char Refined Letter])
  }

  "String checks" should "pass for refined types" in {
    assert(refineV[StartsWith["s"]]("Sandra") == Left("Predicate failed: \"Sandra\".startsWith(\"s\")."))
    assert(refineV[IPv6]("127.0.0.1") == Left("Predicate failed: 127.0.0.1 is a valid IPv6."))
    assert(myName.isInstanceOf[String Refined StartsWith["S"]])
    assert(myName2.isInstanceOf[String Refined EndsWith["t"]])
  }

  "Regex checks" should "pass for refined types" in {
    assert(refineV[myIntRegex]("9742B") == Left("Right predicate of (\"9742B\".matches(\"[A-Za-z0-9]+\") && isValidValidInt(\"9742B\")) failed: ValidInt predicate failed: For input string: \"9742B\""))
    assert(accessCode.isInstanceOf[String Refined myRegex])
    
    assert(accessCode2.isInstanceOf[String Refined myIntRegex])
  }

  "collection checks" should "pass for refined types" in {
    assert(forall.isInstanceOf[Either[String,Refined[List[String],Forall[Trimmed]]]])
    assert(last.isInstanceOf[Either[String,Refined[List[String],Last[Uuid]]]])
    assert(sizeTest.isInstanceOf[Either[String,Refined[List[String],Size[Less[5]]]]])
  }

  "Person checks" should "pass for custom refined types" in {
    assert(tall.isInstanceOf[Either[String,Refined[Person,Tall]]])
    assert(average.isInstanceOf[Either[String,Refined[Person,Average]]])
  }
}
