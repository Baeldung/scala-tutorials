package com.baeldung.scala3.contextualabstractions
import com.baeldung.scala3.contextualabstractions.Givens.Item
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GivensUsingClauseUnitTest extends AnyWordSpec with Matchers {

  "listItems method" should {
    "take the given parameter ordering and sort the list of items" in {
      import Givens.{priceOrdering, pageLimit}

      val shoppingCart = List(
        Item("PanCake", 4),
        Item("Coke", 1),
        Item("Pizza", 5),
        Item("Burger", 3)
      )
      List(Item("Coke", 1.0), Item("Burger", 3.0)) shouldEqual UsingClause
        .listItems(shoppingCart)
    }
  }
}
