package com.baeldung.scala.voidtypes

object NilEmptyList extends App{

  val myList:List[String] = Nil
  println("a list is initialized with length %s".format(myList.length))

  val mySecondList:List[String] = List()

  val consList = "A" :: "B" :: Nil
  println(consList)

}
