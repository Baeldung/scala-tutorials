package com.baeldung.scala.traits.classes


object PartiallyImplemented {

  trait Foo1 {
    def int: Int  // abstract member
    val string: String = "hello"  // non-abstract member
  }

  abstract class Foo2 {
    def bool: Boolean  // abstract member
    val unit: Unit = ()  // non-abstract member
  }

}

object ConstructionParameters {
  abstract class Student(name: String) {
    def printName: Unit = println(name)
  }

  class Senior(name: String) extends Student(name)

  val jeremy = new Senior("jeremy")
}

object ConstructionParametersTrait {
  trait Student {
    def name: String
    def printName: Unit = println(name)
  }

  class Senior(_name: String) extends Student { val name: String = _name }

  val jeremy = new Senior("jeremy")
}

object MultipleArguments {
  abstract class Multiple(a: Int)(b: Int)

  class Baz extends Multiple(1)(2)
}

object MultipleInheritance {
  trait A
  trait B
  trait C

  class Foo extends A with B with C

}

object MixedExample {
  trait A
  trait B
  abstract class X
  abstract class Y

  class Impl extends X with A with B  // valid
  // class Impl2 extends X with Y // will not compile
}
