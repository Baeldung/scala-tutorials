package com.baeldung.scala.mutability

import org.scalatest.FunSuite
import scala.collection.mutable.ArrayBuffer

class MutableCollectionsUnitTest extends FunSuite {
  test("Mutable collection can be added with new elements") {
    val breakfasts = ArrayBuffer("Sandwich", "Salad")

    breakfasts += "Bagels"
    assert(breakfasts == ArrayBuffer("Sandwich", "Salad", "Bagels"))

    breakfasts ++= Seq("PB & J", "Pancake")
    assert(
      breakfasts == ArrayBuffer(
        "Sandwich",
        "Salad",
        "Bagels",
        "PB & J",
        "Pancake"
      )
    )
  }

  test("Mutable collection's elements can be updated") {
    val breakfasts =
      ArrayBuffer("Sandwich", "Salad", "Bagels", "PB & J", "Pancake")
    breakfasts.update(2, "Steak")
    assert(
      breakfasts == ArrayBuffer(
        "Sandwich",
        "Salad",
        "Steak",
        "PB & J",
        "Pancake"
      )
    )
  }

  test("Mutable collection elements can be removed") {
    val breakfasts =
      ArrayBuffer("Sandwich", "Salad", "Steak", "PB & J", "Pancake")

    breakfasts -= "PB & J"
    assert(breakfasts == ArrayBuffer("Sandwich", "Salad", "Steak", "Pancake"))

    breakfasts -= "Fried rice"
    assert(breakfasts == ArrayBuffer("Sandwich", "Salad", "Steak", "Pancake"))
  }

  test("Array can be updated but not added") {
    val lunches = Array("Pasta", "Rice", "Hamburger")

    lunches.update(0, "Noodles")
    assert(lunches sameElements Array("Noodles", "Rice", "Hamburger"))
  }
}
