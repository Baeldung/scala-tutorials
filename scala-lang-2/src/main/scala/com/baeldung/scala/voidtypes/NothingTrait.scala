package com.baeldung.scala.voidtypes

object NothingTrait extends App {

  val nothingList = List[Nothing]()
  println(nothingList)

  def logException(e:Exception):Nothing = {
    println("logging Exception: %s".format(e.getMessage))
    throw new Exception("My New Exception")
  }

}
