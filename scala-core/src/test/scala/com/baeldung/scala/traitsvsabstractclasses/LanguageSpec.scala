package com.baeldung.scala.traitsvsabstractclasses

import org.scalatest.GivenWhenThen
import org.scalatest.funspec.AnyFunSpec

class LanguageSpec extends AnyFunSpec with GivenWhenThen {

  describe("Languages") {
    Given("three different instances of Language")
    val french: Language = new French
    val german: Language = new German
    // spanish instantiates an anonymous (abstract) class:
    val spanish: Language = new Language(name = "Spanish", helloString = "") {
      override def hello(recipient: String): String = s"¡Buen día, $recipient!"
      override def goodbye: String = "¡Hasta luego!"
    }

    When("a salutation in French is requested")
    val frenchSalutation = french.hello("Pierre")

    Then("a salutation in French for Pierre should be received")
    assert(frenchSalutation == "Bon jour, Pierre")

    When("a salutation in German is requested")
    val germanSalutation = german.hello("Peter")

    Then("a salutation in German for Peter should be received")
    assert(germanSalutation == "Guten Tag, Peter")

    When("a salutation in Spanish is requested")
    val spanishSalutation = spanish.hello("Pedro")

    Then("a salutation in Spanish for Pedro should be received")
    assert(spanishSalutation == "¡Buen día, Pedro!")

    When("an abstract Language is instantiated")
    Then("the code shouldn't even compile")
    assertDoesNotCompile(
      """val hungarian = new Language(name = "Hungarian", helloString = "")"""
    )
  }

}
