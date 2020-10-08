package com.baeldung.scala.voidtypes

object NothingTrait extends App {

  val nothingList = List[Nothing]()
  println(nothingList)

  val nn:Nothing = {throw new Exception}

}
