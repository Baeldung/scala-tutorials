package com.baeldung.scala.strings.capitalize

object CapitalizeWords {

  def capitalizeWords(sentence: String): String = {
    sentence.split("\\s+").map(_.capitalize).mkString(" ")
  }
}
