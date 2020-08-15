package com.baeldung.scala.bounds

object implicitvalues {
  def printDebugMsg(msg: String)(implicit debugging: Boolean): Unit = {
    if (debugging) println(msg) else ()
  }

  implicit val debugging: Boolean = true

  // Second parameter passed implicitly
  printDebugMsg("I am debugging this method")
}
