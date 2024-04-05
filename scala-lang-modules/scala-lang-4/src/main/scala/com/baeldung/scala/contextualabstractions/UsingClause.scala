package com.baeldung.scala.contextualabstractions

import Givens.Item
import Givens.{Item, priceOrdering, pageLimit}

object UsingClause extends App {

  def listItems(
    products: Seq[Item]
  )(using ordering: Ordering[Item])(using limit: Int) = {
    products.sorted.take(limit)
  }

  val shoppingCart = List(
    Item("PanCake", 4),
    Item("Coke", 1),
    Item("Pizza", 5),
    Item("Burger", 3)
  )

  val sortedItems = listItems(shoppingCart)
  println(sortedItems) // prints -> List(Item(Coke,1.0), Item(Burger,3.0))
}
