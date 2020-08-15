package com.baeldung.scala.bounds

object multipleparameterlists {
  val amIcool: Boolean = true

  def cond(pred: => Boolean)(proc: => Unit): Unit = {
    if (pred) proc else ()
  }

  cond(amIcool) {
    println("You are cool")
  }
}
