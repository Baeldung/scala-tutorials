package com.baeldung.scala.inheritance

object Hybrid {
  trait A {
    val a = "A"
  }

  trait B extends A {
    val b = "B"
    override val a: String = "b.a"
  }

  trait C extends A {
    val c = "C"
    override val a: String = "c.a"
  }

  trait D extends B with C
  trait E extends C with B

  def main(args: Array[String]): Unit = {
    val d = new D {}
    val e = new E {}
    println(d.a) // prints c.a
    println(e.a) // prints b.a
  }
}
