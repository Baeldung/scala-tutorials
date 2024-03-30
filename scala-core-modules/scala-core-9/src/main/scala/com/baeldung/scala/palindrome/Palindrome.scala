package com.baeldung.scala.palindrome

import scala.annotation.tailrec

object Palindrome {

  extension (str: String) {
    def sanitize: String = str.replaceAll("[^A-Za-z0-9]", "").toLowerCase
  }

  def isPalindromeByReverse(str: String): Boolean = {
    val sanitizedStr = str.sanitize
    sanitizedStr == sanitizedStr.reverse
  }

  def isPalindromeByRecursion(str: String): Boolean = {
    val sanitizedStr = str.sanitize
    @tailrec
    def isPalindromeRec(str: String): Boolean = {
      str match {
        case _ if str.length <= 1      => true
        case _ if str.head == str.last => isPalindromeRec(str.tail.init)
        case _                         => false
      }
    }
    isPalindromeRec(sanitizedStr)
  }

  def isPalindromeByForAll(str: String): Boolean = {
    val sanitizedStr = str.sanitize
    sanitizedStr.zipWithIndex.forall { case (ch, i) =>
      ch == sanitizedStr.charAt(sanitizedStr.length - i - 1)
    }
  }

}
