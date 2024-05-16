package com.baeldung.scala.async

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.should.Matchers
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.wordspec.{AnyWordSpec, AsyncWordSpec}

import scala.async.Async.{async, await}
import scala.concurrent.Future

object ScalaAsyncTest {
  import scala.concurrent.ExecutionContext.Implicits.global

  def slowComputation: Future[Int] = Future {
    Thread.sleep(1000)
    10
  }

  def anotherSlowComputation(s: String): Future[Int] = Future {
    Thread.sleep(1500)
    s.length
  }

  def sequentialCombination: Future[Int] = async {
    await(slowComputation) + await(anotherSlowComputation("Baeldung"))
  }

  def parallelCombination: Future[Int] = async {
    val r1 = slowComputation
    val r2 = anotherSlowComputation("Baeldung")
    await(r1) + await(r2)
  }

  /* Uncommenting this snippet will produce the following error:
  await must not be used under a nested method. await(slowComputation)

  def invalid = async {
    def localFunction = {
      await(slowComputation)
    }

    localFunction
  }*/

  /* Uncommenting this snippet will produce the following error:
  await must not be used under a try/catch. await(slowComputation)

  def tryCatch = async {
    try {
      await(slowComputation)
    } catch {
      case e: Throwable => println(e.getMessage)
    }
  }*/

  def withFor: Future[Int] = for {
    r1 <- slowComputation
    r2 <- anotherSlowComputation("Baeldung")
  } yield r1 + r2
}

class ScalaAsyncUnitTest extends AsyncWordSpec with Matchers with ScalaFutures {

  import ScalaAsyncTest._

  implicit private val defaultPatience: PatienceConfig =
    PatienceConfig(timeout = Span(3, Seconds), interval = Span(500, Millis))

  "Futures combination" should {
    "work sequentially" in {
      sequentialCombination.map { r =>
        assert(r == 18)
      }
    }

    "work in parallel" in {
      parallelCombination.map { r =>
        assert(r == 18)
      }
    }

    "give the same result as Futures composed with for comprehension" in {
      withFor.map { r =>
        assert(r == 18)
      }
    }
  }
}
