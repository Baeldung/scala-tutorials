package com.baeldung.scala.stackabletrait

object StackableTraitExample {
  trait IntTransformation {
    def transform(value: Int): Int = value
  }

  trait DoubleTransformation extends IntTransformation {
    override def transform(value: Int): Int =
      super.transform(value * 2)
  }

  trait LogInt extends IntTransformation {
    override def transform(value: Int): Int = {
      println(s"Transforming value: $value")
      super.transform(value)
    }
  }

  trait CustomTransformation(f: Int => Int) extends IntTransformation {
    override def transform(value: Int): Int =
      super.transform(f(value))
  }

  @main
  def mainST(): Unit = {
    val logAndDouble = new IntTransformation
      with DoubleTransformation
      with LogInt {}
    val doubleAndLog = new IntTransformation
      with LogInt
      with DoubleTransformation {}
    val logAndCustom = new IntTransformation
      with CustomTransformation(_ + 1)
      with LogInt {}

    println(s"Log and double: ${logAndDouble.transform(5)}")
    println(s"Double and log: ${doubleAndLog.transform(5)}")
    println(s"Log and increment: ${logAndCustom.transform(5)}")
  }
}
