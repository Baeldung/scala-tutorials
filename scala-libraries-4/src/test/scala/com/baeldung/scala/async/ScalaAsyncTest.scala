package com.baeldung.scala.async

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.should.Matchers
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.wordspec.AnyWordSpec

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

  /*def invalid = async {
    def localFunction = {
      await(slowComputation)
    }

    localFunction
  }

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

class ScalaAsyncTest extends AnyWordSpec with Matchers with ScalaFutures {

  import ScalaAsyncTest._

  implicit private val defaultPatience: PatienceConfig =
    PatienceConfig(timeout = Span(3, Seconds), interval = Span(500, Millis))

  "Futures combination" should {
    "work sequentially" in {
      whenReady(sequentialCombination) { r =>
        assert(r == 18)
      }
    }

    "work in parallel" in {
      whenReady(parallelCombination) { r =>
        assert(r == 18)
      }
    }

    "give the same result as Futures composed with for comprehension" in {
      whenReady(withFor) { r =>
        assert(r == 18)
      }
    }
  }
}
