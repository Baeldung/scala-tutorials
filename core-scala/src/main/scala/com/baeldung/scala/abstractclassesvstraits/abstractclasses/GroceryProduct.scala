package com.baeldung.scala.abstractclassesvstraits.abstractclasses

abstract class GroceryProduct(sku: String) {
  val price: Double // abstract field
  val tax: Double = 0.23 // concrete field
  def calculateTaxFee(): Double = price * tax // concrete method
}

abstract class Product {
  val sku: String
  val price: Double
}

abstract class TaxCalculator { self: Product => // self type
  val tax: Double
  def calculateTaxFee(): Double = price * tax
}

class Orange(val sku: String, val price: Double) extends GroceryProduct("40303")
