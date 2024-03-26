package com.baeldung.scala.spire.monoids

import com.baeldung.scala.spire.monoids.AdditiveMonoid.*
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import spire.implicits._
import spire.math._

class AdditiveMonoidUnitTest extends AnyWordSpec with Matchers {

  "sum" should {
    "add integers" in {
      val integers = List(1, 2, 3, 4, 5)
      val sumInt = sum(integers)
      sumInt shouldEqual 15
    }
    "add BigDecimals" in {
      val decimals = List(BigDecimal(1.5), BigDecimal(2.5), BigDecimal(3.5))
      val sumBigDecimal = sum(decimals)
      sumBigDecimal shouldEqual BigDecimal(7.5)
    }
  }
}
