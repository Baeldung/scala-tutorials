package com.baeldung.scala.applyfunction

object ApplyFunction extends App {

  val doubleFunction: Function1[Int, Int] = x => 2 * x

  doubleFunction.apply(2) // returns 4

  val baeldung = Employee("baeldung")
  val baeldungBlog = Blog("baeldung")
  val doubler = Doubler(2)
}

class Blog(name: String)
object Blog {
  def apply(name: String) = new Blog(name)
}

case class Employee(name: String)
object Doubler {
  def double(number: Int): Int = 2 * number
  def apply(number: Int): Int = 2 * number
}
