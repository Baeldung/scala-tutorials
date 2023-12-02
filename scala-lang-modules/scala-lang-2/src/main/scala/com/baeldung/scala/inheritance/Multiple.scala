package com.baeldung.scala.inheritance

object Multiple {
  trait A {
    val a = "A"
  }

  trait B {
    val b = "A"
  }

  class C extends A with B {
    val c = "C"
  }

  def main(args: Array[String]): Unit = {
    val c = new C
  }
}
