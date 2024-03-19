package com.baeldung.scala.powerof2

import scala.annotation.tailrec

object PowerOfTwo {

  def isPowerOfTwoByDivision(num: Long): Boolean = {
    @tailrec
    def isPowerRec(n: Long): Boolean = {
      if (n == 1) true
      else if (n % 2 == 0) isPowerRec(n / 2)
      else false
    }
    num > 0 && isPowerRec(num)
  }

  def isPowerOfTwoByCountingOnes(num: Long): Boolean = {
    num > 0 && num.toBinaryString.count(_ == '1') == 1
  }

  def isPowerOfTwoByBitwiseAnd(num: Long): Boolean = {
    num > 0 && (num & (num - 1)) == 0
  }

}
