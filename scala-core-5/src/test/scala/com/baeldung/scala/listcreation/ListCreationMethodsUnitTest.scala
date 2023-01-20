package com.baeldung.scala.listcreation

import com.baeldung.scala.listcreation.ListCreationMethods.{
  createListUsingApply,
  createListUsingCons,
  createListUsingFill,
  createListUsingTabulate,
  createListUsingToList
}
import org.scalatest.{FlatSpec, Matchers}

class ListCreationMethodsUnitTest extends FlatSpec with Matchers {

  "createListUsingCons" should "be able to create a new list using the cons (::) operator" in {
    val listUsingCons = createListUsingCons()
    val expectedList: List[String] = List("Truck", "Car", "Bike")
    listUsingCons shouldEqual expectedList
  }

  "createListUsingApply" should "be able to create a new list using the .apply() method" in {
    val listUsingApply = createListUsingApply()
    val expectedList: List[String] = List("Truck", "Car", "Bike")
    listUsingApply shouldEqual expectedList
  }

  "createListUsingFill" should "be able to create a new list using the .fill() method" in {
    val listUsingFill = createListUsingFill()
    val expectedList: List[String] = List("", "", "")
    listUsingFill shouldEqual expectedList
  }

  "createListUsingToList" should "be able to create a new list using the .toList() method" in {
    val listUsingToList = createListUsingToList()
    val expectedList: List[String] = List("Truck", "Car", "Bike")
    listUsingToList shouldEqual expectedList
  }

  "createListUsingTabulate" should "be able to create a new list using the .tabulate() method" in {
    val listUsingTabulate = createListUsingTabulate()
    val expectedList: List[String] = List("0", "1", "10")
    listUsingTabulate shouldEqual expectedList
  }

}
