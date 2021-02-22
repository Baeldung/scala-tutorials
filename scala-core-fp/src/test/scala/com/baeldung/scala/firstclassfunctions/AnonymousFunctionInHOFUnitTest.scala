package com.baeldung.scala.firstclassfunctions

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class AnonymousFunctionInHOFUnitTest extends AnyFlatSpec with Matchers {
  "Map function" should "take anonymous function as parameter" in {
    val listOfNumbers = List(1, 2, 3, 4, 5)
    val transformedListOfNumbers = List(2, 3, 4, 5, 6)
    assert(AnonymousFunctionInHOF.mapOperation(listOfNumbers) == transformedListOfNumbers)
  }

  "Filter function" should "take anonymous function as parameter" in {
    val listOfNumbers = List(10, 8, 36, 25, 12)
    val filteredListOfNumbers = List(10, 25)
    assert(AnonymousFunctionInHOF.filterOperation(listOfNumbers) == filteredListOfNumbers)
  }

  "Fold function" should "take anonymous function as parameter" in {
    val listOfNumbers = List(2, 3, 4)
    val accumulatedResult = 29
    assert(AnonymousFunctionInHOF.foldOperation(listOfNumbers) == accumulatedResult)
  }
}
