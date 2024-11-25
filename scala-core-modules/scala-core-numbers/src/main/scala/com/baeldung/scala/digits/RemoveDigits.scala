package com.baeldung.scala.digits

object RemoveDigits {

  def removeFirstNDigits(num: Int, n: Int): Int = {
    num.toString.drop(n).toInt
  }

  def removeFirstNDigitsLimit(num: Int, n: Int): Int = {
    val numString = num.toString
    if (n >= numString.length)
      0
    else
      numString.drop(n).toInt
  }

  def removeFirstNDigitsPatternMatching(num: Int, n: Int): Int = {
    val numString = num.toString
    numString match {
      case str if n >= str.length =>
        0
      case str =>
        str.drop(n).toInt
    }
  }

  def removeFirstNDigitsNegative(num: Int, n: Int): Int = {
    val numString = num.toString
    if (num < 0) {
      // For negative numbers, remove the first n digits but keep the negative sign
      val absNumString = numString.drop(1) // Drop the negative sign
      if (n >= absNumString.length)
        0
      else
        ("-" + absNumString.drop(n)).toInt
    } else {
      // For positive numbers, apply the same logic as before
      if (n >= numString.length)
        0
      else
        numString.drop(n).toInt
    }
  }

}
