package com.baeldung.scala.inheritance

object Multilevel {
  class A() {
    val a = "A"
  }

  class B() extends A() {
    val b = "B"
  }

  class C() extends B() {
    val c = "C"
  }

  def main(args: Array[String]): Unit = {
    val c = new C
    println(s"c.a = ${c.a}") // A property (non direct superclass)
    println(s"c.b = ${c.b}") // B property (direct superclass)
    println(s"c.c = ${c.c}") // C property
  }
}
