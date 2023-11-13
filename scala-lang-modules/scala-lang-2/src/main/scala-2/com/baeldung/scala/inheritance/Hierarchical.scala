package com.baeldung.scala.inheritance

object Hierarchical {
  class A() {
    val a = "A"
  }

  class B() extends A() {
    val b = "B"
  }

  class C() extends A() {
    val c = "C"
  }

  def main(args: Array[String]): Unit = {
    val b = new B
    val c = new C
    println(s"b.a = ${b.a}")
    println(s"b.a = ${b.b}")
    println(s"c.a = ${c.a}")
    println(s"c.c = ${c.c}")
  }
}
