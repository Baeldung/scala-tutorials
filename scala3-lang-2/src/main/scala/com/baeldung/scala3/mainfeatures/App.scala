package com.baeldung.scala3.mainfeatures

import java.util
import java.util.regex.Pattern
import scala.jdk.CollectionConverters.*

trait Item {
  val price: Int
}

class DiscountedItem(override val price: Int, discount: Int) extends Item {
  def discountedPrice: Int = price - discount
}

object App {
  def sum(ns: Seq[Int]): Int = {
    var total = 0
    for (i <- ns)
      total += i
    total
  }

  def sum2(ns: Seq[Int]): Int = ns.foldLeft(0) { case (total, i) => total + i }

  def length(value: String | Int): Int =
    value match {
      case s: String => s.length
      case n: Int    => n.toString.length
    }

  extension (s: String) {
    def removeOccurrences(subString: String): String = s.replace(subString, "")
    def removeOccurrencesIgnoreCase(subString: String) =
      Pattern
        .compile(subString, Pattern.CASE_INSENSITIVE)
        .matcher(s)
        .replaceAll("")
  }

  @main
  def main(): Unit = {
    assert(sum(Seq(1, 2, 3, 4)) == 10)

    assert(DiscountedItem(10, 4).discountedPrice == 6)

    val javaList = util.LinkedList[Int]()
    javaList.add(1)
    javaList.add(2)
    val scalaList: List[Int] = javaList.asScala.toList
    assert(scalaList == List(1, 2))

    assert(length("Test") == 4)
    assert(length(10) == 2)

    assert("ThisIsJustATest".removeOccurrences("Just") == "ThisIsATest")
    assert(
      "ThisIsJustATest".removeOccurrencesIgnoreCase("just") == "ThisIsATest"
    )
  }
}
