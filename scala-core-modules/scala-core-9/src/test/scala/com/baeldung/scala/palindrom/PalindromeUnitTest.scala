package com.baeldung.scala.palindrom

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import com.baeldung.scala.palindrome.Palindrome.*

class PalindromeUnitTest
  extends AnyFlatSpec
  with Matchers
  with TableDrivenPropertyChecks {

  private val functions = Seq(
    ("isPalindromeByReverse", isPalindromeByReverse),
    ("isPalindromeByRecursion", isPalindromeByRecursion),
    ("isPalindromeByForAll", isPalindromeByForAll)
  )

  private val wordsTable = Table(
    ("Word", "Is Palindrome"),
    ("racecar", true),
    ("Madam", true),
    ("AaAA", true),
    ("AAbaa", true),
    ("mala  yalam", true),
    ("racecar!!", true),
    ("madam1", false),
    ("   ", true),
    ("", true),
    ("@#$%", true),
    ("hello", false),
    ("10101", true),
  )

  functions.foreach { (name, fn) =>
    it should s"check if a string is palindrome using the function ${name}" in {
      forAll(wordsTable) { (str, isPalindrome) =>
        fn(str) shouldBe isPalindrome
      }
    }
  }

}
