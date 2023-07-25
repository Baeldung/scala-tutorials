package com.baeldung.scala

object ScalaJsApp {
  def main(args: Array[String]): Unit = {
    generateTextParagraph()
  }
  def generateTextParagraph(): Unit = {
    import org.scalajs.dom.document
    val paragraph = document.createElement("p")
    paragraph.textContent = "This is a test paragraph."
    document.body.appendChild(paragraph)
  }
}
