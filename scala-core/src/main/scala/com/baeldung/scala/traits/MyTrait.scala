package com.baeldung.scala.traits

trait A

trait B

trait C

class D extends A with B with C

class Car

trait MyTrait { def age(name: String): Int }

trait FastCar {

  println("We are driving fast")

}

object TestCar extends App {

  val fastCar = new Car with FastCar

}
