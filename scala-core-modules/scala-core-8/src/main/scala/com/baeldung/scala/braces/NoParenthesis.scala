package com.baeldung.scala.braces

object NoParenthesis extends App {
  def greet(name: String): Unit = {
    println(s"Hello, $name!")
  }

  // This won't work
  // greet "Alice"

  // But this works and it equivalent to greet("Alice")
  this `greet` "Alice"

}
