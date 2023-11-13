package com.baeldung.scala3.traits

object ParameterizedTrait {
  trait Base(val msg: String)

  class Foo extends Base("Foo")

  class Bar extends Base("Bar")
}
