package com.baeldung.scala.factorial

import scala.annotation.tailrec

object FactorialCalculation {

  def factorialUsingRecursion(num: Int): BigInt = {
    require(num >= 0, "Factorial is not defined for negative numbers")
    if (num == 0) 1
    else num * factorialUsingRecursion(num - 1)
  }

  def factorialUsingTailRecursion(num: Int): BigInt = {
    require(num >= 0, "Factorial is not defined for negative numbers")
    @tailrec
    def fac(n: Int, acc: BigInt): BigInt = {
      n match {
        case 0   => acc
        case num => fac(num - 1, acc * num)
      }
    }
    fac(num, BigInt(1))
  }

  def factorialUsingProduct(num: Int): BigInt = {
    require(num >= 0, "Factorial is not defined for negative numbers")
    num match {
      case 0 => BigInt(1)
      case _ => (BigInt(1) to BigInt(num)).product
    }
  }

  def factorialUsingReduce(num: Int): BigInt = {
    require(num >= 0, "Factorial is not defined for negative numbers")
    num match {
      case 0 => BigInt(1)
      case _ => (BigInt(1) to BigInt(num)).reduce(_ * _)
    }
  }
}
