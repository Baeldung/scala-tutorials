package com.baeldung.scala.retry

import com.baeldung.scala.retry.PrimeNumberRetry.success
import org.mockito.Mockito
import org.mockito.Mockito.{times, verify}
import org.scalatest.Succeeded
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AsyncWordSpec
import org.scalatestplus.mockito.MockitoSugar.mock

import scala.concurrent.Future

class PrimeNumberRetryTest extends AsyncWordSpec with Matchers {

  trait MockClass {
    def invoke(): Future[Int]
  }

  "PrimeNumberRetry" should {

    "FailFast when an NumberFormatException is thrown" in {
      val mockObject = mock[MockClass]
      Mockito
        .when(mockObject.invoke())
        .thenReturn(Future { throw new NumberFormatException })

      val result = PrimeNumberRetry.outerPolicy.apply(mockObject.invoke())
      result
        .map(_ => assert(false))
        .recover {
          case _: NumberFormatException =>
            verify(mockObject, times(1)).invoke()
            Succeeded
        }
    }

    // Retry library will retry up to 5 times after the first failure, so we have 6 invocations in failures
    "retry in any exception" in {
      val mockObject = mock[MockClass]
      Mockito
        .when(mockObject.invoke())
        .thenReturn(Future { throw new IllegalArgumentException })

      val result = PrimeNumberRetry.outerPolicy.apply(mockObject.invoke())
      result
        .map(_ => assert(false))
        .recover {
          case _: IllegalArgumentException =>
            verify(mockObject, times(7)).invoke()
            Succeeded
        }
    }

    "succeed when a prime is returned" in {
      val mockObject = mock[MockClass]
      Mockito
        .when(mockObject.invoke())
        .thenReturn(Future { 7 })

      val result = PrimeNumberRetry.outerPolicy.apply(mockObject.invoke())
      result
        .flatMap(_ => {
          verify(mockObject, times(1)).invoke()
          Succeeded
        })
    }

    "fail when a non prime number is returned" in {
      val mockObject = mock[MockClass]
      Mockito
        .when(mockObject.invoke())
        .thenReturn(Future { 10 })

      val result = PrimeNumberRetry.outerPolicy.apply(mockObject.invoke())
      result
        .flatMap(number => {
          verify(mockObject, times(7)).invoke()
          assert(number == 10)
        })
    }
  }
}
