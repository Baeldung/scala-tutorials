package com.baeldung.scala.strings.capitalize

object CapitalizeWords {

  def capitalizeWords(sentence: String): String = {
    sentence.split("\\s+").map(_.capitalize).mkString(" ")
  }

  def capitalizeTitleCase(sentence: String): String = {
    val exclusions = Set("is", "in", "to", "a", "an", "the")
    sentence
      .split("\\s+")
      .zipWithIndex
      .map { (word, index) =>
        if (index != 0 && exclusions.contains(word.toLowerCase)) word
        else word.capitalize
      }
      .mkString(" ")
  }

  def capitalizeWordsPreserveSpaces(sentence: String): String = {
    sentence.zipWithIndex.map { (char, index) =>
      if(index == 0) char.toUpper
      else if(sentence.charAt(index-1).isSpaceChar) char.toUpper
      else char
    }.mkString
  }
}
