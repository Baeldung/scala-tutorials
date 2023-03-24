package com.baeldung.scala.pathdependenttypes

class Foo {
  class Bar
}

case class Pair[T1, T2](a: T1, b: T2) {
  def equalTypes(implicit ev: T1 =:= T2): Unit = {}
}

object PathDependentTypeInnerClasses extends App {
  val f1 = new Foo
  val b1: f1.Bar = new f1.Bar

  val f2 = new Foo
  val b2: f2.Bar = new f2.Bar

  // Cannot compile:
  // Pair(b1, b2).equalTypes
}
