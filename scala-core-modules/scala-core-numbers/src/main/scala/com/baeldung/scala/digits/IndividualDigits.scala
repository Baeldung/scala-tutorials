package com.baeldung.scala.digits

import scala.annotation.tailrec

object IndividualDigits {

  def getDigitsByTailRecursion(num: Long): List[Int] = {
    @tailrec
    def rec(num: Long, digits: List[Int]): List[Int] = {
      if (num < 10) {
        num.toInt :: digits
      } else {
        rec((num / 10), (num % 10).toInt :: digits)
      }
    }
    rec(num, Nil)
  }

  def getDigitsByRecursion(num: Long): List[Int] = {
    if (num < 10) {
      List(num.toInt)
    } else {
      getDigitsByRecursion(num / 10) ++ List((num % 10).toInt)
    }
  }

  def getDigitsByToString(num: Long): List[Int] = {
    num.toString.map(_.asDigit).toList
  }

}
