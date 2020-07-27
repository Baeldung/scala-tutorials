package com.baeldung.scala.bounds

object implicitparameters {
  val amIcool: Boolean = true

  def cond(pred: => Boolean)(proc: => ()): Unit = {
    if (pred) proc else ()
  }

  cond(amIcool) {
    println("You are cool")
  }
}
