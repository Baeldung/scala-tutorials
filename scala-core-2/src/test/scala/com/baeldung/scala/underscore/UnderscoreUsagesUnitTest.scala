package com.baeldung.scala.underscore

import com.baeldung.scala.underscore.UnderscoreUsages._
import org.scalatest.{Matchers, WordSpec}

class UnderscoreUsagesUnitTest extends  WordSpec with Matchers {

  "The underscore" should {
    "match all types in existential types" in {
      getLength(List(List(8), List("str"))) shouldBe 2
      getLength(List(List(5.00), List("str"))) shouldBe  2
      getLength(List(List(Array(7)), List("str"))) shouldBe  2
    }
    "catch all possible cases" in {
      itemTransaction(130) shouldBe  "Buy"
      itemTransaction(150) shouldBe "Sell"
      itemTransaction(89.00) shouldBe "Need approval"
      itemTransaction(1000.99) shouldBe "Need approval"
    }
    "hide unused parameters" in {
      val ints = (1 to 4).map(_ => "Int")
      ints shouldBe Vector("Int", "Int", "Int", "Int")
    }
    "ignore the parameters" in {
      val prices = Seq(10.00, 23.38, 49.82)
      val pricesToInts = prices.map(_.toInt)
      pricesToInts shouldBe Seq(10, 23, 49)
    }
    "access nested the items of nested seq" in {
      val items = Seq(("candy", 2, true), ("cola", 7, false), ("apple", 3, false), ("milk", 4, true))
      val itemsToBuy = items
        .filter(_._3)
        .filter(_._2 > 3)
        .map(_._1)
      itemsToBuy shouldBe Seq("milk")
    }
    "ignore variable for constructed entries" in {
      var text = "a,b"
      val Array(a, _) = text.split(",")
      a shouldBe "a"

      val Array(_, b) = text.split(",")
      b shouldBe "b"

      text = "a,b,c,d,e"
      val Array(a2, _*) = text.split(",")
      a2 shouldBe "a"

      val Array(a3, b3, _, d, e) = text.split(",")
      a3 shouldBe "a"
      b3 shouldBe "b"
      d shouldBe "d"
      e shouldBe "e"
    }
    "work in reassigning a a function to a value" in {
      val times = multiplier _
      multiplier(8, 13) shouldBe times(8, 13)
    }
    "work in converting a sequence to variable arguments" in {
      val sumable = Seq(4, 5, 10, 3)
      val sumOfSumable = sum(sumable: _*)
      sumOfSumable shouldBe 22
    }
    "generate a partially applied function" in {
      val sumToTen = sum(10,_:Int)
      val sumFiveAndTen = sumToTen(5)
      sumFiveAndTen shouldBe 15

      val foo = bar(1,2) _
      foo("Some string", "Another string")(3/5, 6/5) shouldBe 1
    }
    "work in overriding a method's setter" in {
      val product = new Product

      product.price = 20
      product.price shouldBe 20

      try {
        product.price = 7 // will fail because 7 is not greater than 10
        fail("Price must be greater than 10")
      } catch {
        case _: IllegalArgumentException => product.price shouldNot(equal(7))
      }
    }
    "let operators be used a variable name" in {
      val concatenatedList = list_++(List(2, 5))
      concatenatedList shouldBe List(2, 5, 2, 5)
    }
    "work in defining a higher-kinded type" in {
      var seqIsEmpty = SeqContainer.checkIfEmpty(Seq(7, "7"))
      seqIsEmpty shouldBe false
      seqIsEmpty = SeqContainer.checkIfEmpty(Seq())
      seqIsEmpty shouldBe true
    }
  }

}
