package com.baeldung.scala.braces

object ScalaBlockExamples extends App {
  // A simple block returning a value
  val a = {
    val x = 5
    val y = 10
    // The last expression is the value of the block
    x + y
  }
  println(s"Value of a: $a") // Output: Value of a: 15

  // Block in an if-else construct
  val number = 7
  val result = if (number > 5) {
    // This block is executed because the condition is true
    "Greater than 5"
  } else {
    "Not greater than 5"
  }
  println(s"Result: $result") // Output: Result: Greater than 5

  // Block in a function definition
  def square(x: Int) = {
    // A block defining the body of the function
    x * x
  }
  println(s"Square of 4: ${square(4)}") // Output: Square of 4: 16

  // Block as an argument to a higher-order function
  val numbers = List(1, 2, 3, 4, 5)
  val doubledNumbers = numbers.map { n =>
    // A block that is passed as a lambda to the map function
    n * 2
  }
  println(
    s"Doubled numbers: $doubledNumbers"
  ) // Output: Doubled numbers: List(2, 4, 6, 8, 10)

  // Block in a for-comprehension
  val squares = for {
    n <- numbers
    if n % 2 == 0 // Filtering even numbers
  } yield {
    // A block that computes the square of each number
    n * n
  }

  println(s"Squares of even numbers: $squares")
  // Output: Squares of even numbers: List(4, 16)

}
