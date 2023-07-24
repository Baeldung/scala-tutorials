package com.baeldung.scala.traits

class MultipleInheritance extends Trait1 with Trait2 {
  override def method1(): String = "Trait1 method"

  override def method2(): String = "Trait2 method"
}

trait Trait1 {
  def method1(): String
}

trait Trait2 {
  def method2(): String
}
