package com.baeldung.scala.tabledata

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

class TableDrivenUnitTest
  extends AnyFlatSpec
  with Matchers
  with TableDrivenPropertyChecks {

  def isPalindrome(str: String): Boolean = str == str.reverse

  private val palindromeTable = Table(
    ("Text", "Is Palindrome"),
    ("madam", false),
    ("Madam", false),
    ("Hello", true)
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
  it should "check for palindrome for string 'Madam'" in {
    isPalindrome("Madam") shouldBe false
  }
  it should "check for palindrome for string 'Hello'" in {
    isPalindrome("Hello") shouldBe false
  }

}
