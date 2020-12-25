package com.baeldung.scala.cats.functors

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ListFunctorSpec extends AnyFlatSpec with Matchers {
  "ListFunctor" should "transform list" in {
    val list: List[Int] = List(1, 2, 3, 4, 5)
    val transformedList = List(2, 4, 6, 8, 10)
    assert(ListFunctor.transformList(list) == transformedList)
  }
}
