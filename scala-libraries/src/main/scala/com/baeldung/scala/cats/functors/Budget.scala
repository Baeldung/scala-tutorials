package com.baeldung.scala.cats.functors

case class LineItem(price: Double)

object Budget {
  def calcBudget(lineItem: LineItem): LineItem = lineItem.copy(price = lineItem.price * 0.5)
}
