package com.baeldung.scala.tabledata

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

class TableDrivenUnitTest
  extends AnyFlatSpec
  with Matchers
  with TableDrivenPropertyChecks {

  def isPalindrome(str: String): Boolean = {
    val sanitizedStr = str.replaceAll("[^A-Za-z0-9]", "").toLowerCase
    sanitizedStr == sanitizedStr.reverse
  }

  private val palindromeTable = Table(
    ("Text", "Is Palindrome"),
    ("madam", true),
    ("Madam", true),
    ("Tacocat", true),
    ("TACO, CAT", true),
    ("Hello", false),
  )

  it should "check for palindrome" in {
    forAll(palindromeTable) { (str, expectedResult) =>
      isPalindrome(str) shouldBe expectedResult
    }
  }

  it should "check for palindrome using forEvery" in {
    forEvery(palindromeTable) { (str, expectedResult) =>
      isPalindrome(str) shouldBe expectedResult
    }
  }

  it should "check for palindrome for string 'madam'" in {
    isPalindrome("madam") shouldBe true
  }
  it should "check for palindrome for string 'Tacocat' with special character" in {
    isPalindrome("Taco, cat") shouldBe true
  }
  it should "check for palindrome for string 'Hello'" in {
    isPalindrome("Hello") shouldBe false
  }

}
