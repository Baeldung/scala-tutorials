package com.baeldung.scala.strings.keywordsearch

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class KeywordMatchingUnitTest extends AnyFlatSpec with Matchers {

  "Keyword Matching" should "detect keywords in a string using brute-force" in {
    val text = "May the Force be with you"
    val keys = List("Force", "Wakanda", "Hobbit")

    val words = text.split(" ")
    var containsKeyword = false

    for (word <- words) {
      for (key <- keys) {
        if (word == key) {
          containsKeyword = true
        }
      }
    }

    assert(containsKeyword)
  }

  it should "detect keywords using Scala's built-in methods" in {
    val text = "May the Force be with you"
    val keys = List("Force", "Wakanda", "Hobbit")

    val containsKeyword = keys.exists(key => text.split(" ").contains(key))
    assert(containsKeyword)
  }

  it should "optimize keyword detection using Scala collections" in {
    val text = "May the Force be with you"
    val keys = List("Force", "Wakanda", "Hobbit").toSet

    val words = text.split(" ").toSet
    val containsKeyword = keys.exists(key => words.contains(key))

    assert(containsKeyword)
  }

  // Add other tests as needed
}
