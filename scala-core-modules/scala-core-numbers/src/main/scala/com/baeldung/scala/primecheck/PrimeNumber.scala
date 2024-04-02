package com.baeldung.scala.primecheck

object PrimeNumber {
  def isPrime(num: Long): Boolean = {
    if (num <= 1) false
    else if (num == 2 || num == 3) true
    else
      (2L to Math.sqrt(num).toLong).forall(num % _ != 0)
  }

  def isPrimeOptimized(num: Long): Boolean = {
    if (num < 2) false
    else if (num == 2 || num == 3) true
    else if (num % 2 == 0 || num % 3 == 0) false
    else
      (5 to Math.sqrt(num).toInt by 6).forall(n =>
        num % n != 0 && num % (n + 2) != 0
      )
  }

  def isPrimeUsingBigInt(num: Long): Boolean = {
    if (num < 0) false
    else
      BigInt(num).isProbablePrime(certainty = 100)
  }
}
