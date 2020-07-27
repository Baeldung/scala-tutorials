package com.baeldung.scala.bounds

object implicitvalues {
  def printDebugMsg(msg: String)(implicit debugging: Boolean): Unit = {
    if(debugging) println(msg) else ()
  }

  implicit val debugging: Boolean = true

  printDebugMsg("I am debugging this method")
}
