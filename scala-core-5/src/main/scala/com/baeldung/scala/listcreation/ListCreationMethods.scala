package com.baeldung.scala.listcreation

object ListCreationMethods extends App{

  def createListUsingCons(): List[String] = {
    val vehiclesList:List[String] = "Truck" :: "Car" :: "Bike" :: Nil
    vehiclesList
  }

  def createListUsingApply(): List[String] = {
    val vehiclesList:List[String] =  List.apply("Truck", "Car", "Bike")
    vehiclesList
  }

  def createListUsingRange(): List[Int] = {
    val numList:List[Int] =  List.range(1, 5)
    numList
  }

  def createListUsingFill(): List[String] = {
    val filledList:List[String] =  List.fill(3)("")
    filledList
  }

  def createListUsingToList(): List[String] = {
    val convertedList:List[String] =  Array("Truck", "Car", "Bike").toList
    convertedList
  }

  def createListUsingTabulate(): List[String] = {
    val tabulatedList:List[String] =  List.tabulate(3) (n => n.toBinaryString)
    tabulatedList
  }

  println(createListUsingCons())  // prints List("Truck","Car","Bike")
  println(createListUsingApply())  // prints List("Truck","Car","Bike")
  println(createListUsingRange())  // prints List(1, 2, 3, 4)
  println(createListUsingFill())   // prints List("","","")
  println(createListUsingToList()) // prints List("Truck","Car","Bike")
  println(createListUsingTabulate())  // prints List(0, 1, 10)
}
