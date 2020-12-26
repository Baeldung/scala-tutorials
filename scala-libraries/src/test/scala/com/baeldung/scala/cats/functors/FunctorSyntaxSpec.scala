package com.baeldung.scala.cats.functors

import cats.implicits._
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.Future

class FunctorSyntaxSpec extends AsyncFlatSpec with Matchers {

  "FunctorSyntax" should "transform list of line items" in {
    val lineItemsList = List(LineItem(10.0), LineItem(20.0))
    val result = FunctorSyntax.withFunctor(lineItemsList, Budget.calcBudget)
    assert(result == List(LineItem(5.0), LineItem(10.0)))
  }

  it should "transform option of line item" in {
    val maybeLineItem: Option[LineItem] = Some(LineItem(10))
    val result = FunctorSyntax.withFunctor(maybeLineItem, Budget.calcBudget)
    assert(result.contains(LineItem(5)))
  }

  it should "transform future of line item" in {
    val eventualLineItem: Future[LineItem] = Future.successful {
      LineItem(10)
    }
    val result = FunctorSyntax.withFunctor(eventualLineItem, Budget.calcBudget)
    result.map(res => assert(res == LineItem(5)))
  }
}
