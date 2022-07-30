package com.baeldung.scala

import org.scalajs.dom.document
import org.scalatest.flatspec.AnyFlatSpec

class ScalaJsUnitTest extends AnyFlatSpec {
  "Scala Js" should "generate test paragraph with expected text" in {
    ScalaJsApp.generateTextParagraph()
    val paragraphs = document.querySelectorAll("p")
    val paragraphsWithText = paragraphs.count(_.textContent == "This is test paragraph.")
    assert(paragraphsWithText == 1)
  }
}
