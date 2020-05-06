package com.baeldung.scala.monocle

import org.scalatest._
import org.scalatest.Assertions._
import scala.util.{Success, Failure}
import OpticsExamples._

class OpticsExamplesUnitTest extends FlatSpec with Matchers {

  "OpticExamples" should "update stock without Lenses" in {
    val currentStock = 10
    val item = Item("123sku", 35L, currentStock, NoDiscount())
    val cart = Cart("abc123", item, 1)
    val user = User("joe", cart)

    val newUser = updateStockWithoutLenses(user)
    assert(newUser.cart.item.leftInStock == currentStock - 1)
  }

  it should "update stock with Lenses" in {
    val currentStock = 10
    val item = Item("123sku", 35L, currentStock, NoDiscount())
    val cart = Cart("abc123", item, 1)
    val user = User("joe", cart)

    val newUser = updateStockWithLenses(user)
    assert(newUser.cart.item.leftInStock == currentStock - 1)
  }

  it should "update discount percentage values" in {
    val originalDiscount = 10L
    val newDiscount = 5L
    val updatedCart = updateDiscountedItemsPrice(
      Cart("abc", Item("item123", 23L, 1, PercentageOff(originalDiscount)), 1),
      newDiscount
    )
    assert(updatedCart.item.discount == PercentageOff(newDiscount))
  }

  it should "not update discount fix price values" in {
    val originalDiscount = 10L
    val newDiscount = 5L
    val updatedCart = updateDiscountedItemsPrice(
      Cart("abc", Item("item123", 23L, 1, FixPriceOff(originalDiscount)), 1),
      newDiscount
    )
    assert(updatedCart.item.discount == FixPriceOff(originalDiscount))
  }

  it should "return the Fix Off discount value" in {
    val value = 3L
    assert(getDiscountValue(FixPriceOff(value)) == Some(value))
  }

  it should "return no discount value" in {
    assert(getDiscountValue(NoDiscount()) == None)
  }

  it should "transform GBP to EUR correctly" in {
    val x =
      tranformCurrency.modify(gbp => gbp.copy(gbp.value + 90L))(PriceEUR(1000L))
    assert(x.value == 1100L)
  }

}
