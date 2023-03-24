package com.baeldung.scala.typeinference

object TypeInferredFunctions extends App {
  def squareInt(num: Int) = {
    num * num
  }
  val square = squareInt(2)
  println(square.getClass) // prints int
}
