package com.baeldung.scala.stackabletrait

object StackableTraitWithExplicitBaseAndCoreExample {
  trait BaseIntTransformation {
    def transform(value: Int): Int
  }

  trait CoreIntTransformation extends BaseIntTransformation {
    def transform(value: Int): Int = value
  }

  trait DoubleTransformation extends CoreIntTransformation {
    override def transform(value: Int): Int =
      super.transform(value * 2)
  }

  trait LogInt extends CoreIntTransformation {
    override def transform(value: Int): Int = {
      println(s"Transforming value: $value")
      super.transform(value)
    }
  }

  trait CustomTransformation(f: Int => Int) extends CoreIntTransformation {
    override def transform(value: Int): Int =
      super.transform(f(value))
  }

  @main
  def mainSTE(): Unit = {
    val logAndDouble = new CoreIntTransformation
      with DoubleTransformation
      with LogInt {}
    val doubleAndLog = new CoreIntTransformation
      with LogInt
      with DoubleTransformation {}
    val logAndCustom = new CoreIntTransformation
      with CustomTransformation(_ + 1)
      with LogInt {}

    println(s"Log and double: ${logAndDouble.transform(5)}")
    println(s"Double and log: ${doubleAndLog.transform(5)}")
    println(s"Log and increment: ${logAndCustom.transform(5)}")
  }
}
