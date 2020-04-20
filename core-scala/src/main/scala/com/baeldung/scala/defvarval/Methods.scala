package com.baeldung.scala.defvarval

object Methods extends App {
  def method: Int = {
    println("method")
    1
  }

  println("After method declaration")
  println(method)
  println(method)
}
