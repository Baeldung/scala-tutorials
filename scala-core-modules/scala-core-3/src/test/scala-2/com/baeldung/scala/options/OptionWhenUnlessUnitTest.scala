package com.baeldung.scala.options

import org.scalatest.funsuite.AnyFunSuite
import OptionBuilder._

class OptionWhenUnlessUnitTest extends AnyFunSuite {

  test("conversion of Int to Option using when with true condition") {
    val num: Int = 25
    val maybePositive = Option.when(num > 0)(num)
    val expected = Some(num)
    val actual = maybePositive
    assert(expected == actual)
  }

  test("conversion of Int to Option using when with false condition") {
    val num: Int = -4
    val maybePositive = Option.when(num > 0)(num)
    val expected = None
    val actual = maybePositive
    assert(expected == actual)
  }

  test("conversion of Int to Option using unless with true condition") {
    val num: Int = 25
    val maybeNegative = Option.unless(num > 0)(num)
    val expected = None
    val actual = maybeNegative
    assert(expected == actual)
  }

  test("conversion of Int to Option using unless with false condition") {
    val num: Int = -4
    val maybeNegative = Option.unless(num > 0)(num)
    val expected = Some(num)
    val actual = maybeNegative
    assert(expected == actual)
  }

  test("conversion of List to Option using when with true condition") {
    val list: List[Int] = List(1, 2, 3)
    val maybeNonEmptyList = Option.when(list.nonEmpty)(list)
    val expected = Some(list)
    val actual = maybeNonEmptyList
    assert(expected == actual)
  }

  test("conversion of List to Option using when with false condition") {
    val list: List[Int] = List(1, 2, 3)
    val maybeEmptyList = Option.when(list.isEmpty)(list)
    val expected = None
    val actual = maybeEmptyList
    assert(expected == actual)
  }

  test("conversion of List to Option using unless with false condition") {
    val list: List[Int] = List(1, 2, 3)
    val maybeNonEmptyList = Option.unless(list.isEmpty)(list)
    val expected = Some(list)
    val actual = maybeNonEmptyList
    assert(expected == actual)
  }

  test(
    "conversion of List to Option using custom when builder with true condition"
  ) {
    val list: List[Int] = List(1, 2, 3)
    val maybeNonEmptyList = list.when(_.nonEmpty)
    val expected = Some(list)
    val actual = maybeNonEmptyList
    assert(expected == actual)
  }

  test(
    "conversion of List to Option using custom unless builder with true condition"
  ) {
    val list: List[Int] = List(1, 2, 3)
    val maybeEmptyList = list.unless(_.nonEmpty)
    val expected = None
    val actual = maybeEmptyList
    assert(expected == actual)
  }

  test(
    "conversion of List to Option using custom when builder with false condition"
  ) {
    val list: List[Int] = List()
    val maybeNonEmptyList = list.when(_.nonEmpty)
    val expected = None
    val actual = maybeNonEmptyList
    assert(expected == actual)
  }

  test(
    "conversion of List to Option using custom unless builder with false condition"
  ) {
    val list: List[Int] = List()
    val maybeEmptyList = list.unless(_.nonEmpty)
    val expected = Some(list)
    val actual = maybeEmptyList
    assert(expected == actual)
  }

  test(
    "conversion of Int to Option using custom when builder with true condition"
  ) {
    val num: Int = 25
    val maybePositive: Option[Int] = num.when(_ > 0)
    val expected = Some(25)
    val actual = maybePositive
    assert(expected == actual)
  }

  test(
    "conversion of Int to Option using custom when builder with false condition"
  ) {
    val num: Int = -4
    val maybePositive: Option[Int] = num.when(_ >= 0)
    val expected = None
    val actual = maybePositive
    assert(expected == actual)
  }

  test(
    "conversion of Int to Option using custom unless builder with true condition"
  ) {
    val num: Int = 25
    val maybeNegative: Option[Int] = num.unless(_ > 0)
    val expected = None
    val actual = maybeNegative
    assert(expected == actual)
  }

  test(
    "conversion of Int to Option using custom unless builder with false condition"
  ) {
    val num: Int = -4
    val maybeNegative: Option[Int] = num.unless(_ >= 0)
    val expected = Some(num)
    val actual = maybeNegative
    assert(expected == actual)
  }
}
