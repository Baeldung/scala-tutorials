package com.baeldung.scala.abstractclassesvstraits.traits

trait Product {
  val sku: String
  val price: Double
}
trait TaxCalculator { self: Product => // self type
  val tax: Double
  def calculateTaxFee(): Double = price * tax
}

trait GroceryProduct extends TaxCalculator with Product

object GroceryProduct {
  class Orange(val sku: String, val price: Double, val tax: Double)
      extends TaxCalculator
      with Product
}
