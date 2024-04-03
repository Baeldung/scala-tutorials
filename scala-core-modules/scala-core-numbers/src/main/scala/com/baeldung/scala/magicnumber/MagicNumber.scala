package com.baeldung.scala.magicnumber

import scala.annotation.tailrec

object MagicNumber {

  def isMagicNumber(sumFn: Long => Long)(num: Long): Boolean = {
    // Only positive numbers are used, so we take abs of any number.
    // Instead of this, we could also add require condition
    // require(num >= 0, "Only positive numbers are allowed!")
    val sum = sumOfDigitsUsingRecursion(Math.abs(num))
    sum == 1
  }

  // Impl 1
  def sumOfDigitsUsingRecursion(num: Long): Long = {

    @tailrec
    def recursiveSum(num: Long, sum: Long): Long = {
      num match {
        case 0 if sum < 10  => sum
        case 0 if sum >= 10 => recursiveSum(sum / 10, sum % 10)
        case n              => recursiveSum(n / 10, sum + (num % 10))
      }
    }
    recursiveSum(num, 0)
  }

  // Impl 2
  @tailrec
  def sumOfDigitsUsingAsDigit(n: Long): Long = {
    if (n < 10) {
      // If the number is a single digit, return it
      n
    } else {
      // Calculate the sum of digits recursively
      val digitSum = n.toString.map(_.asDigit).sum
      sumOfDigitsUsingAsDigit(digitSum)
    }
  }

  // Impl 3
  @tailrec
  def sumOfDigitsUsingFold(num: Long): Long = {
    def sum = num.toString.foldLeft(0)((acc, dig) => acc + dig.asDigit)
    if (sum < 10) sum else sumOfDigitsUsingFold(sum)
  }

  // Impl 4
  def sumOfDigitsUsingIterator(num: Long): Long = {
    Iterator
      .iterate(num)(currentNum => currentNum.toString.map(_.asDigit).sum)
      .dropWhile(_ >= 10)
      .next()
  }

}
