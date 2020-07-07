package com.baeldung.scala.underscore

import com.baeldung.scala.underscore.UnderscoreUsages._
import org.junit.Assert._
import org.junit.Test

class UnderscoreUsagesUnitTest {

  @Test
  def givenGetLength_whenCalledWithAnyNestedList_shouldReturnTheLength(): Unit = {
    assertEquals(getLength(List(List(8), List("str"))), 2)
    assertEquals(getLength(List(List(5.00), List("str"))), 2)
    assertEquals(getLength(List(List(Array(7)), List("str"))), 2)
  }

  @Test
  def givenItemTransaction_whenCalledWithDouble_shouldReturnBuySellOrNeedApproval(): Unit = {
    assertEquals(itemTransaction(130), "Buy")
    assertEquals(itemTransaction(150), "Sell")
    assertEquals(itemTransaction(89.00), "Need approval")
    assertEquals(itemTransaction(1000.99), "Need approval")
  }

  @Test
  def givenASequence_whenMapped_shouldIgnoreParameter(): Unit = {
    val ints = (1 to 4).map(_ => "Int")
    assertEquals(ints, Vector("Int", "Int", "Int", "Int"))
  }

  @Test
  def givenASequence_whenMapped_shouldIgnoreTheParametersInTheAnonymousFunction(): Unit = {
    val prices = Seq(10.00, 23.38, 49.82)
    val pricesToInts = prices.map(_.toInt)
    assertEquals(pricesToInts, Seq(10, 23, 49))
  }

  @Test
  def giveANestedSeq_whenFunctionsAreApplied_shouldBeAbleToAccessMembersUsingUnderscore(): Unit = {
    val items = Seq(("candy", 2, true), ("cola", 7, false), ("apple", 3, false), ("milk", 4, true))
    val itemsToBuy = items
      .filter(_._3)
      .filter(_._2 > 3)
      .map(_._1)
    assertEquals(itemsToBuy, Seq("milk"))
  }

  @Test
  def givenAString_whenSplit_thenNotUsedVariablesCanBeIgnored(): Unit = {
    var text = "a,b"
    val Array(a, _) = text.split(",")
    assertEquals(a, "a")

    val Array(_, b) = text.split(",")
    assertEquals(b, "b")

    text = "a,b,c,d,e"
    val Array(a2, _*) = text.split(",")
    assertEquals(a2, "a")

    val Array(a3, b3, _, d, e) = text.split(",")
    assertEquals(a3, "a")
    assertEquals(b3, "b")
    assertEquals(d, "d")
    assertEquals(e, "e")
  }

  @Test
  def givenTheFunctionMultiplier_usingTheUnderScore_thenItCanBeReassignedToAValue(): Unit = {
    val times = multiplier _
    assertEquals(multiplier(8, 13), times(8, 13))
  }

  @Test
  def givenASeq_usingTheUnderscore_thenItCanBeConvertedToVariableArguments(): Unit = {
    val sumable = Seq(4, 5, 10, 3)
    val sumOfSumable = sum(sumable: _*)
    assertEquals(sumOfSumable, 22)
  }

  @Test
  def givenAFunctionSum2_usingTheUnderscore_thenAPartiallyAppliedFunctionCanBeGenerated(): Unit = {
    val sumToTen = sum(10,_:Int)
    val sumFiveAndTen = sumToTen(5)
    assertEquals(sumFiveAndTen, 15)
  }

  @Test
  def givenAFunctionWithMultipleParameterGroup_usingTheUnderscore_thenAPartiallyAppliedFunctionCanBeGenerated(): Unit = {
    val foo = bar(1,2) _
    assertEquals(foo("Some string", "Another string")(3/5, 6/5), 1)
  }

  @Test
  def givenProduct_usingTheUnderscore_thenPriceSetterCanBeOverride(): Unit = {
    val product = new Product

    product.price = 20
    assertEquals(product.price, 20)

    try {
      product.price = 7 // will fail because 7 is not greater than 10
      fail("Price must be greater than 10")
    } catch {
      case _: IllegalArgumentException => assertNotEquals(product.price, 7)
    }
  }

  @Test
  def givenAVariableName_usingUnderscore_thenOperatorCanBeUsedInTheName(): Unit = {
    val concatenatedList = list_++(List(2, 5))
    assertEquals(concatenatedList, List(2, 5, 2, 5))
  }

  @Test
  def givenATypeCreator_usingUnderscore_thenAHigherKindedTypeCanBeDefined(): Unit = {
    var seqIsEmpty = SeqContainer.checkIfEmpty(Seq(7, "7"))
    assertTrue(seqIsEmpty == false)
    seqIsEmpty = SeqContainer.checkIfEmpty(Seq())
    assertTrue(seqIsEmpty == true)
  }

}
