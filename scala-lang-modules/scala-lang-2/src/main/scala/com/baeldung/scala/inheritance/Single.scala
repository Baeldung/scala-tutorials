package com.baeldung.scala.inheritance

object Single {
  class A() {
    def name: String = "A"
  }

  class B() extends A() {
    override def name: String = "B"
  }

  def main(args: Array[String]): Unit = {
    val b = new B
  }
}
