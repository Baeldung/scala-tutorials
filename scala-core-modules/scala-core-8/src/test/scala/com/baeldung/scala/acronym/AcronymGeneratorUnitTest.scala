package com.baeldung.scala.acronym

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalacheck.Gen
import org.scalatest.matchers.should.Matchers
import AcronymGenerator.*

class AcronymGeneratorUnitTest
  extends AnyFlatSpec
  with ScalaCheckPropertyChecks
  with Matchers {

  val sentenceGenerator: Gen[String] = for {
    charPart <- Gen.alphaStr
    numPart <- Gen.listOfN(5, Gen.numChar).map(_.mkString)
    specialCharPart <- Gen
      .listOfN(1, Gen.oneOf(Seq('@', "_", "*")))
      .map(_.mkString)
    alphaNum <- Gen.alphaNumStr
  } yield s"$charPart $numPart $specialCharPart$charPart $alphaNum"

  it should "generate acronym for random generated string correctly" in {
    forAll(sentenceGenerator) { sentence =>
      println("sentence -=> " + sentence)
      val acronym = AcronymGenerator.acronymUsingSplit(sentence)
      withClue(s"Gen sentence = $sentence , acronym = $acronym") {
        acronym.forall(_.isLetter) shouldBe true
        acronym.exists(_ == ' ') shouldBe false
        acronym.forall(_.isUpper) shouldBe true
      }
    }
  }

  it should "generate empty acronym for empty string" in {
    val acronym = acronymUsingSplit("    ")
    acronym shouldBe empty
  }

  it should "generate correct acronym for alpha numeric string" in {
    val acronym = acronymUsingSplit("This is a sentence with 9numbers")
    acronym shouldBe "TIASW"
  }

  it should "generate empty acronym for numeric/special char starting string" in {
    val acronym = acronymUsingSplit("1is 7m @num")
    acronym shouldBe empty
  }

  it should "generate acronym for FAQ" in {
    val acronym = acronymUsingSplit("Frequently Asked Questions")
    acronym shouldBe "FAQ"
  }

  it should "generate acronym for NASA with extra spaces in between" in {
    val acronym =
      acronymUsingSplit("National Aeronautics & Space   Administration ")
    acronym shouldBe "NASA"
  }

}
