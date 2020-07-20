package com.baeldung.scala.traits

object StackableTraits extends App {

  class Fruit {
    def qualities() = List("healthy")
  }

  trait Sweet extends Fruit {
    override def qualities() = super.qualities() :+ "sweet"
  }

  trait Tasty extends Fruit {
    override def qualities() = super.qualities() :+ "tasty"
  }

  val mango = new Fruit with Tasty with Sweet
  println(mango.qualities())
  //output: List(healthy, tasty, sweet)

  val pineapple = new Fruit with Sweet with Tasty
  println(pineapple.qualities())
  //output: List(healthy, sweet, tasty)

}
