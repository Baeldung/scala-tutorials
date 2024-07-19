package com.baeldung.scala.romannumerals

import scala.annotation.tailrec
import scala.math.BigDecimal.int2bigDecimal

object NumberToRomanNumeral {

  private val numbersToRomans = List(
    (1000, "M"),
    (900, "CM"),
    (500, "D"),
    (400, "CD"),
    (100, "C"),
    (90, "XC"),
    (50, "L"),
    (40, "XL"),
    (10, "X"),
    (9, "IX"),
    (5, "V"),
    (4, "IV"),
    (1, "I")
  )

  def usingRecursion(num: Int): String = {
    numbersToRomans.find((n, _) => {
      num - n >= 0
    }) match
      case Some((n, r)) => r + usingRecursion(num - n)
      case None         => ""
  }

  def usingTailRecursion(num: Int): String = {
    @tailrec
    def recursiveFn(remaining: Int, acc: String): String = {
      numbersToRomans.find((n, _) => {
        remaining - n >= 0
      }) match
        case Some((n, r)) => recursiveFn(remaining - n, acc + r)
        case None         => acc
    }
    recursiveFn(num, "")
  }

  def usingFold(num: Int): String = {
    numbersToRomans
      .foldLeft((num, ""))((remainingAndAcc, numAndRoman) => {
        val (remaining, acc) = remainingAndAcc
        if (remaining == 0) remainingAndAcc
        else {
          val (n, r) = numAndRoman

          (remaining % n, acc + r * (remaining / n))
        }
      })
      ._2
  }

}
