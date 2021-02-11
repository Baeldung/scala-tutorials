package com.baeldung.scala.firstclassfunctions

object PartiallyAppliedFunction
{
    def calculateSellingPrice(discount: Double, productPrice: Double): Double =
    {
        (1 - discount/100) * productPrice
    }
}
