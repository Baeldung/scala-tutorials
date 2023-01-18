package com.baeldung.scala.await

import java.util.concurrent.TimeoutException

import org.scalatest.{ Matchers, WordSpec, Ignore }

import scala.concurrent.duration._
import scala.concurrent.{ Await, Future }

@Ignore // fixing in JAVA-9842
class AwaitFutureUnitTest extends AwaitFutureTestUtil {

  private val url = "http://www.baeldung.com"
  "Using Await.ready" should {
    "return original completed future" in {
      val fut = AwaitFuture.fetchDataFrom(url)

      fut.isCompleted shouldBe false
      val completedFuture = Await.ready(fut, 2.seconds)

      fut shouldBe completedFuture
      completedFuture.isCompleted shouldBe true
      completedFuture.isInstanceOf[Future[String]] shouldBe true
      val assertion = completedFuture.value match {
        case Some(result) => result.isSuccess
        case _ => false
      }
      assertion shouldBe true
    }

    "throw a timeout Exception when the future doesn't complete within the stipulated time" in {
      val fut = AwaitFuture.fetchDataFrom(url, 3000)
      fut.isCompleted shouldBe false
      val exception = intercept[Exception](Await.ready(fut, 2.seconds))

      val assertion = exception match {
        case _ : TimeoutException => true
        case _ => false
      }

      assertion shouldBe true
    }

    "not throw an exception when future fails but return a failed future " in {
      val fut = AwaitFuture.futureWithException()

      fut.isCompleted shouldBe false
      val completedFuture = Await.ready(fut, 2.seconds)

      fut shouldBe completedFuture
      completedFuture.isCompleted shouldBe true
      val assertion = completedFuture.value match {
        case Some(result) => result.isFailure
        case _ => false
      }
      assertion shouldBe true
    }
  }

  "Using Await.result" should {
    "return content of future " in {
      val fut = AwaitFuture.fetchDataFrom(url)

      fut.isCompleted shouldBe false
      val completedFutureResult = Await.result(fut, 2.seconds)
      completedFutureResult.isInstanceOf[String] shouldBe true
    }
    "throw a timeout Exception when the future doesn't complete within the stipulated time" in {
      val fut = AwaitFuture.fetchDataFrom("https://www.facebook.com", 5000)
      fut.isCompleted shouldBe false
      val exception = intercept[Exception](Await.result(fut, 2.seconds))

      val assertion = exception match {
        case _ : TimeoutException => true
        case _ => false
      }
      assertion shouldBe true
    }

    "throw an exception when future fails with an exception" in {
      val fut = AwaitFuture.futureWithException()

      fut.isCompleted shouldBe false
      val exception = intercept[Exception](Await.result(fut, 2.seconds))

      val assertion = exception match {
        case _ : NullPointerException => true
        case _ => false
      }
      assertion shouldBe true
    }
  }

}

trait AwaitFutureTestUtil extends WordSpec with Matchers {
  //needed to avoid a 403 error
  System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36")
}
