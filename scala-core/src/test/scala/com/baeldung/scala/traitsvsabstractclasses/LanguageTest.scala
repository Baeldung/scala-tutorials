package com.baeldung.scala.traitsvsabstractclasses

import org.scalatest.funsuite.AnyFunSuite

class LanguageTest extends AnyFunSuite {

  test("testHello") {
    val french: Language = new French
    val german: Language = new German

    // spanish instantiates an anonymous (abstract) class:
    val spanish: Language = new Language(name = "Spanish", helloString = "") {
      override def hello(recipient: String): String = s"¡Buen día, $recipient!"
      override def goodbye: String = "¡Hasta luego!"
    }

    assertResult("Bon jour, Pierre")(french.hello("Pierre"))
    assertResult("Guten Tag, Peter")(german.hello("Peter"))
    assertResult("¡Buen día, Pedro!")(spanish.hello("Pedro"))

    assertDoesNotCompile(
      // hungarian wouldn't compile because an abstract class can't be instantiated:
      """val hungarian = new Language(name = "Hungarian", helloString = "")"""
    )
  }

}
