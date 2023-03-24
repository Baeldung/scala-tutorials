package com.baeldung.scala.retry

import retry.{Policy, Success}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.Random

object PrimeNumberRetry {

  implicit val success: Success[Int] = {
    val successA = retry.Success[Int](_ == 0)
    val successB = retry.Success[Int](isPrime)
    successA.or(successB)
  }

  def generateRandomNumber: Future[Int] =
    outerPolicy.apply(Future {
      val number = Random.nextInt()
      if (number < 0) {
        throw new UnsupportedOperationException(
          s"Got a negative number: $number"
        )
      } else if (number > 100) {
        throw new IllegalArgumentException(
          s"Expected number within the [0-100] range. Got $number"
        )
      }
      number
    })

  def isPrime(number: Int): Boolean = {
    var prime = true
    for (i <- 2 until number if number % i == 0) {
      prime = false
    }
    prime
  }

  val policy: Policy = retry
    .When {
      case _: UnsupportedOperationException =>
        retry.Backoff.apply(5, 5.milliseconds)
      case _: IllegalArgumentException =>
        retry.Directly.apply(5)
      case _ =>
        retry.JitterBackoff.apply(5, 5.milliseconds)
    }

  val outerPolicy: Policy = retry.FailFast(policy) {
    case _: NumberFormatException => true
  }
}
