package com.baeldung.scala.strings.keywordsearch

import scala.collection.parallel.CollectionConverters._
import scala.io.Source

object KeywordCounter extends App {

  // Load the text of "Pride and Prejudice" from Project Gutenberg
  val url = "https://www.gutenberg.org/files/1342/1342-0.txt"
  val text = Source.fromURL(url).mkString

  val keywords = List("Darcy", "Bennet", "Pemberley", "Bingley", "Wickham")

  // Split the text into words and transform it into a parallel collection
  val words = text.split("\\W+").par

  // Count occurrences of each keyword
  val counts = keywords.par.map { keyword =>
    keyword -> words.count(_ == keyword)
  }.seq // Convert back to a sequential map for consistent output

  counts.foreach { case (keyword, count) =>
    println(s"$keyword: $count")
  }
}
