package com.baeldung.scala.pimpmylib

import com.baeldung.scala.pimpmylib.PimpLibExample._
import org.scalatest.flatspec.AnyFlatSpec

class PimpLibExampleUnitTest extends AnyFlatSpec{

  "Bot" should "detect new member details" in {
    val intro = IntroText(
      "My name is Fauz my primary programming language is Scala I have 7 years of industry experience"
    )

    assertResult(expected = "Senior")(actual = intro.level)
    assertResult(expected = "Scala")(actual = intro.language)
    assertResult(expected = "Fauz")(actual = intro.name)
  }

  "Pimped Bot" should "detect new member details" in {
    val intro =
      "My name is Bengi my primary programming language is Java I have 2 years of industry experience"

    assertResult(expected = "Junior")(actual = intro.level)
    assertResult(expected = "Java")(actual = intro.language)
    assertResult(expected = "Bengi")(actual = intro.name)
  }
}
