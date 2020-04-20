package com.baeldung.scala.defvarval

object Variables extends App {
  var variable: Int = {
    println("variable")
    1
  }

  println("After variable declaration")
  println(variable)
  println(variable)

  variable = 2
  println(variable)
}
