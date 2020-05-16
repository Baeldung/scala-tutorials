package com.baeldung.scala.mutability

object MutableCollections {
  def main(args: Array[String]): Unit = {
    import scala.collection.mutable.ArrayBuffer

    val breakfasts = ArrayBuffer("Sandwich", "Salad")
    println(breakfasts)

    breakfasts += "Bagels"
    println(breakfasts)

    breakfasts ++= Seq("PB & J", "Pancake")
    println(breakfasts)

    breakfasts.update(2, "Steak")
    println(breakfasts)

    breakfasts -= "PB & J"
    println(breakfasts)

    breakfasts -= "Fried rice"
    println(breakfasts)

    val lunches = Array("Pasta", "Rice", "Hamburger")
    println(lunches.mkString(","))

    lunches.update(0, "Noodles")
    println(lunches.mkString(","))
  }
}
