package com.baeldung.scala.firstclassfunctions

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PartiallyAppliedFunctionSpec extends AnyFlatSpec with Matchers
{
    "Partially applied function" should "calculate selling price after discount" in
    {
        val discountApplied = PartiallyAppliedFunction.calculateSellingPrice(25, _)
        val sellingPrice = discountApplied(1000)
        assert(sellingPrice == 750)
    }
}
