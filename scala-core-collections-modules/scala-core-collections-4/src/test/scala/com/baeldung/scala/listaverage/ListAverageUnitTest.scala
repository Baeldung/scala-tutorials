package com.baeldung.scala.listaverage

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ListAverageUnitTest extends AnyFlatSpec with Matchers {
  val lst = List(1, 2, 3, 4)

  "naive" should "return rounded average 2" in {
    ListAverage.naive(lst) shouldBe 2
  }

  "naive with double" should "return average 2.5" in {
    ListAverage.averageDouble(lst) shouldBe 2.5
  }

  "averageWithListMethods" should "return rounded average 2" in {
    ListAverage.averageWithListMethods(lst) shouldBe 2
  }

  "averageWithListMethodsDouble" should "return average 2.5" in {
    ListAverage.averageWithListMethodsDouble(lst) shouldBe 2.5
  }

  "averageWithFold" should "return average 2.5" in {
    ListAverage.averageWithFold(lst) shouldBe 2.5
  }

}
