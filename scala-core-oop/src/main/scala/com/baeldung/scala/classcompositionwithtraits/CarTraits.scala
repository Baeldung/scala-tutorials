package com.baeldung.scala.classcompositionwithtraits

object CarTraits {

  /**
    * There are a few examples of traits to describe an entity of a Car below
    * @author Sergey Ionin
    */
  /**
    * Trait with abstract members and one method
    */
  trait Car {
    def model: String

    def horsePower: Int
  }

  /**
    * Another trait to be mixed into the class
    */
  trait SimpleMarshaller extends Car {
    def toJson: String =
      s"{${"\"model\""}:$model," +
        s"\n${"\"horsePower\""}:$horsePower}"
  }

  /**
    * Another trait with self-type to be mixed into the class
    */
  trait Marshaller {
    self: Car =>
    def toJson =
      s"{${"\"model\""}:$model," +
        s"\n${"\"horsePower\""}:$horsePower}"
  }

  /**
    * The traits below will show that the matter of mixing matters (see the tests)
    */
  trait Printable {
    self: Car =>
    def print(appendix: String = ""): String =
      s"the model $model has $horsePower HP under the hood. $appendix"
  }

  trait PrettyPrintable extends Printable {
    self: Car =>
    override def print(appendix: String = "You'll definitelly enjoy driving!") =
      super.print(appendix)
  }

  trait ShortPrintable extends Printable {
    self: Car =>
    override def print(appendix: String) =
      if (appendix.length() > 10) super.print()
      else
        super.print(appendix)
  }

  // Implementations

  class BMW0(val model: String, val horsePower: Int) extends Car

  class BMWPrintable(val model: String, val horsePower: Int)
      extends Car
      with Printable {
    def printInfo() = print()
  }

  object BMW {

    final case class F15(model: String = "F15", horsePower: Int = 309)
        extends Car
        with Printable

    final case class X7(model: String = "X7", horsePower: Int = 335)
        extends Car
        with Printable

  }

}
