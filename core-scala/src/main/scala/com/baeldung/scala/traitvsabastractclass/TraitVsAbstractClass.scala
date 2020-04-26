package com.baeldung.scala.traitvsabastractclass;

object TraitVsAbstractClass {

  abstract class Shape(name: String, color: String){
    def perimeter: Double
    def area: Double
    def description(): Unit = {
      println(s"I am a ${color} ${name}.")
    }
  }

  class Square(length: Double, name: String, color: String) extends Shape(name, color) {
    def perimeter: Double = length * length
    def area: Double = length * 4
  }


  trait Shape2 {
    val color: String
    var name: String
    def perimeter: Double
    def area: Double
    def description(): Unit = {
      println(s"I am a ${color} ${name}.")
    }
  }

  class Square2(length: Double, shape_name: String, shape_color: String) extends Shape2 {
    override val color: String = shape_color
    var name = shape_name
    def perimeter: Double = length * length
    def area: Double = length * 4
  }


  trait A {
    def a: String = "I am from trait A"
  }

  trait B {
    def b: String = "I am from trait B"
  }

  trait C {
    def c: String = "I am from trait C"
  }

  class D(d: String) extends A with B with C {
    def d(): Unit = {
      println(d)
    }
  }


  def main(args: Array[String]): Unit = {
    var square = new Square(3.00, "square", "green")
    println(square.area)
    println(square.perimeter)
    square.description()
    println()

    var square2 = new Square2(3.00, "square", "green")
    println(square2.area)
    println(square2.perimeter)
    square2.description()
    println()

    var d = new D("I am from class D")
    println(d.a)
    println(d.b)
    println(d.c)
    d.d()
    println()

    println("-----------Done-------------")
    println()
  }
}
