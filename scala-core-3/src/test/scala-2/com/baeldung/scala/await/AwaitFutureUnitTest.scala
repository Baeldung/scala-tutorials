package com.baeldung.scala.await

<<<<<<< HEAD:scala-core-3/src/test/scala/com/baeldung/scala/await/AwaitFutureUnitTest.scala
import org.scalatest.Ignore
=======
import org.scalatest.{Ignore, Retries}
>>>>>>> 96fe189b888478a6d1a6b969a60b245b66f9308d:scala-core-3/src/test/scala-2/com/baeldung/scala/await/AwaitFutureUnitTest.scala
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.{AnyWordSpec, AnyWordSpecLike}
import org.scalatest.tagobjects.Retryable
import org.scalatest.tags.Retryable

import java.util.concurrent.TimeoutException
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
@Retryable
class AwaitFutureUnitTest
  extends AwaitFutureTestUtil
  with Matchers
<<<<<<< HEAD:scala-core-3/src/test/scala/com/baeldung/scala/await/AwaitFutureUnitTest.scala
  with AnyWordSpecLike {
=======
  with AnyWordSpecLike
  with Retries {

  override def withFixture(test: NoArgTest) = {
    if (isRetryable(test))
      withRetry {
        super.withFixture(test)
      }
    else
      super.withFixture(test)
  }
>>>>>>> 96fe189b888478a6d1a6b969a60b245b66f9308d:scala-core-3/src/test/scala-2/com/baeldung/scala/await/AwaitFutureUnitTest.scala

  private val url = "http://www.baeldung.com"
  "Using Await.ready" should {
    "return original completed future" in {
      val fut = AwaitFuture.fetchDataFrom(url, 500)

      fut.isCompleted shouldBe false
      val completedFuture = Await.ready(fut, 2.seconds)

      fut shouldBe completedFuture
      completedFuture.isCompleted shouldBe true
      completedFuture.isInstanceOf[Future[String]] shouldBe true
      val assertion = completedFuture.value match {
        case Some(result) => result.isSuccess
        case _            => false
      }
      assertion shouldBe true
    }

    "throw a timeout Exception when the future doesn't complete within the stipulated time" in {
      val fut = AwaitFuture.fetchDataFrom(url, 3000)
      fut.isCompleted shouldBe false
      val exception = intercept[Exception](Await.ready(fut, 2.seconds))

      val assertion = exception match {
        case _: TimeoutException => true
        case _                   => false
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
        case _            => false
      }
      assertion shouldBe true
    }
  }

  "Using Await.result" should {
    "return content of future " in {
      val fut = AwaitFuture.fetchDataFrom(url, 500)

      fut.isCompleted shouldBe false
      val completedFutureResult = Await.result(fut, 2.seconds)
      completedFutureResult.isInstanceOf[String] shouldBe true
    }
    "throw a timeout Exception when the future doesn't complete within the stipulated time" in {
      val fut = AwaitFuture.fetchDataFrom("https://www.facebook.com", 5000)
      fut.isCompleted shouldBe false
      val exception = intercept[Exception](Await.result(fut, 2.seconds))

      val assertion = exception match {
        case _: TimeoutException => true
        case _                   => false
      }
      assertion shouldBe true
    }

    "throw an exception when future fails with an exception" in {
      val fut = AwaitFuture.futureWithException()

      fut.isCompleted shouldBe false
      val exception = intercept[Exception](Await.result(fut, 2.seconds))

      val assertion = exception match {
        case _: NullPointerException => true
        case _                       => false
      }
      assertion shouldBe true
    }
  }

}

trait AwaitFutureTestUtil extends AnyWordSpec with Matchers {
  // needed to avoid a 403 error
  System.setProperty(
    "http.agent",
    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36"
  )
}
