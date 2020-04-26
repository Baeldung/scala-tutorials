package com.baeldung.scala.abstractclassesvstraits

object CarTraits {

  /**
   * There are a few examples of traits to describe an entity of a Car below
   */

  /**
   * Trait with abstract members and one method
   */
  trait Car {
    def model: String

    def horsePower: Int

    def print = s"the model $model has $horsePower HP under the hood"
  }

  class BMW0(val model: String, val horsePower: Int) extends Car

  object BMW {

    final case class F15(model: String = "F15", horsePower: Int = 309) extends Car

    final case class X7(model: String = "X7", horsePower: Int = 335) extends Car

  }

  /**
   * Another trait to be mixed into the class
   */
  trait Marshaller {
    self: Car =>
    def toJson = s"{${"\"model\""}:$model," +
      s"\n${"\"horsePower\""}:$horsePower}"
  }

}
