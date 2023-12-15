package com.baeldung.scala.acronym

object AcronymGenerator {
  def acronymUsingSplit(text: String): String = {
    text
      .split("\\s")
      .filterNot(_.trim.isEmpty)
      .map(_.head)
      .filter(_.isLetter)
      .mkString
      .toUpperCase
  }
}
