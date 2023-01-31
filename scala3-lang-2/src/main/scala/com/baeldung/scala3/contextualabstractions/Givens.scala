package com.baeldung.scala3.contextualabstractions

object Givens extends App {

  case class Item(name: String, price: Double)

  given priceOrdering: Ordering[Item] = new Ordering[Item] {
    override def compare(i1: Item, i2: Item): Int = i1.price.compareTo(i2.price)
  }

  given pageLimit: Int = 2

  // Anonymous Givens
  given Item = Item("Dummy", 0.0)
}
