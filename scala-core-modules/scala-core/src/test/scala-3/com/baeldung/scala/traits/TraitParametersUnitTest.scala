package com.baeldung.scala.traits

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TraitParametersUnitTest extends AnyFlatSpec with Matchers {

  "An Author" should "return a proper introduction" in {
    val author = new Author("Mark Twain")
    val introduction = author.introduce

    introduction shouldEqual "Hello, I'm Mark Twain"
  }

  "A Poet" should "return a proper introduction" in {
    val poet = new Poet("Sylvia Plath")
    val introduction = poet.introduce

    introduction shouldEqual "Hello, I'm Sylvia Plath"
  }
}
