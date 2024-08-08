package com.baeldung.scala.stackabletrait

object MixinExample {
  trait Person {
    val name: String
    val country: String
  }

  case class ItalianPerson(name: String) extends Person {
    val country = "Italy"
  }

  trait WithPrettyPrinting extends Person {
    def prettyPrint: String =
      s"""Name: $name
        |Country: $country""".stripMargin
  }

  @main
  def main(): Unit =
    val italian = new ItalianPerson("Mario") with WithPrettyPrinting
    println(italian)
    println(italian.prettyPrint)
}
