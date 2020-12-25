package com.baeldung.scala.cats.functors

import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.Future

class FutureFunctorSpec extends AsyncFlatSpec with Matchers {
  "FutureFunctor" should "transform future" in {
    val future: Future[Int] = Future {
      10
    }
    val transformedFutureResult = 11
    FutureFunctor.transformFuture(future).map(result => assert(result == transformedFutureResult))
  }
}
