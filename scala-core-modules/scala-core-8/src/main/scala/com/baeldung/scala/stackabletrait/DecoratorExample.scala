package com.baeldung.scala.stackabletrait

object DecoratorExample {
  trait IntTransformation {
    def transform(value: Int): Int = value
  }

  class TransformationDecorator(wrappee: IntTransformation)
    extends IntTransformation {
    override def transform(value: Int): Int = wrappee.transform(value)
  }

  class DoubleDecorator(wrappee: IntTransformation)
    extends TransformationDecorator(wrappee) {
    override def transform(value: Int): Int =
      super.transform(value * 2)
  }

  class LogInt(wrappee: IntTransformation)
    extends TransformationDecorator(wrappee) {
    override def transform(value: Int): Int = {
      println(s"Transforming value: $value")
      super.transform(value)
    }
  }

  class CustomDecorator(f: Int => Int, wrappee: IntTransformation)
    extends TransformationDecorator(wrappee) {
    override def transform(value: Int): Int =
      super.transform(f(value))
  }

  @main
  def mainDec(): Unit = {
    val identity = new IntTransformation {}

    val withLogging = new LogInt(identity)
    val withDouble = new DoubleDecorator(withLogging)
    val withCustom = new CustomDecorator(_ + 1, withDouble)

    println(s"With increment, double, and logging: ${withCustom.transform(5)}")
  }
}
