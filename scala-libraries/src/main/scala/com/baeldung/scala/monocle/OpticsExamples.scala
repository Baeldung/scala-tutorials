package com.baeldung.scala.monocle

import monocle.{Iso, Lens, Prism, Optional}
import monocle.macros.GenLens
import monocle.macros.Lenses

object OpticsExamples {

  trait Discount
  case class NoDiscount() extends Discount
  case class PercentageOff(value: Double) extends Discount
  case class FixPriceOff(value: Double) extends Discount

  case class User(name: String, cart: Cart)
  case class Cart(id: String, item: Item, quantity: Int)

  case class Item(
      sku: String,
      price: Double,
      leftInStock: Int,
      discount: Discount
  )

  def updateStockWithoutLenses(user: User): User = {
    user.copy(
      cart = user.cart.copy(
        item = user.cart.item.copy(leftInStock = user.cart.item.leftInStock - 1)
      )
    )
  }

  def updateStockWithLenses(user: User): User = {

    val cart: Lens[User, Cart] = GenLens[User](_.cart)
    val item: Lens[Cart, Item] = GenLens[Cart](_.item)
    val leftInStock: Lens[Item, Int] = GenLens[Item](_.leftInStock)

    (cart composeLens item composeLens leftInStock).modify(_ - 1)(user)
  }

  def updateDiscountedItemsPrice(cart: Cart, newDiscount: Double): Cart = {
    val discountLens: Lens[Item, Discount] = GenLens[Item](_.discount)
    val onlyPctDiscount = Prism.partial[Discount, Double] {
      case PercentageOff(p) => p
    }(PercentageOff)

    val newItem =
      (discountLens composePrism onlyPctDiscount set newDiscount)(cart.item)

    cart.copy(item = newItem)
  }

  def getDiscountValue(discount: Discount): Option[Double] = {
    val maybeDiscountValue = Optional[Discount, Double] {
      case pctOff: PercentageOff => Some(pctOff.value)
      case fixOff: FixPriceOff   => Some(fixOff.value)
      case _                     => None
    } { discountValue => discount =>
      discount match {
        case pctOff: PercentageOff => pctOff.copy(value = discountValue)
        case fixOff: FixPriceOff   => fixOff.copy(value = discountValue)
        case _                     => discount
      }
    }

    maybeDiscountValue.getOption(discount)

  }

  case class PriceEUR(value: Double)
  case class PriceGBP(value: Double)

  val tranformCurrency = Iso[PriceEUR, PriceGBP] { eur =>
    PriceGBP(eur.value * 0.9)
  } { gbp => PriceEUR(gbp.value / 0.9) }

}
