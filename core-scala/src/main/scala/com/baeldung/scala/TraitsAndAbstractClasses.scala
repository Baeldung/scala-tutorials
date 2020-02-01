package com.baeldung.scala

object TraitsAndAbstractClasses {
  def main(args: Array[String]): Unit = {
//    val shape = new Shape() // won't compile, Shape is an abstract class
//    val calc = new Calculable() // won't compile, Calculable is a trait
    val dog = new Dog()
    println(dog.sound)
  }

  abstract class Employee(id: String)

  class Intern(id: String, duration: Int) extends Employee(id)

  class Dog {
    def sound = "bark"
  }

  abstract class Shape {
    def area: Double
  }

  trait Calculable {
    def integral: Double
  }

  trait Dimensioned {
    val dimensions: Int
  }

  class Cube(side: Double) extends Calculable with Dimensioned {
    override def integral: Double = side * side * side

    override val dimensions: Int = 2
  }

  class Sphere(radius: Double) extends Shape {
    override def area: Double = 4 * Math.PI * radius * radius
  }

  object RedCylinder extends Dimensioned {
    override val dimensions: Int = 2
  }
}
