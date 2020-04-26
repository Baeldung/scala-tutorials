package com.baeldung.scala.abstractclassesvstraits

object CarAbstractClasses {

  /**
   * Below there are few examples of abstract classes to describe an entity of a Car
   */

  /**
   * (1) Abstract class with constructor parameters
   */
  abstract class Car0(model: String, horsePower: Int)

  class BMW0(val model: String, val horsePower: Int) extends Car0(model, horsePower)

  /**
   * (2) Abstract class with abstract members and implemented method
   */
  abstract class Car1 {
    def model: String

    def horsePower: Int

    def print = s"the model $model has $horsePower HP under the hood"
  }

  class BMW1(val model: String, val horsePower: Int) extends Car1

  class Volvo1(volvoModel: String, volvoHorsePower: Int) extends Car1 {
    val model = volvoModel
    val horsePower = volvoHorsePower
  }

  /**
   * (3) Abstract class with constructor parameters, members and implemented method
   */
  abstract class Car2(val model: String, val horsePower: Int) {
    def print = s"the model $model has $horsePower HP under the hood"
  }

  object BMW {

    object F15 extends Car2("F15", 309)

    object X7 extends Car2("X7", 335)

  }

  object Volvo {

    object XC60 extends Car2("XC60", 250)

    object XC90 extends Car2("XC90", 185)

  }

  /**
   * (4) Abstract class with constructor parameters, members and call by name parameter
   */
  abstract class CarWithMileage(val model: String, val horsePower: Int, mileage: => Int) {
    def printMileage = s"the model $model has mileage of $mileage thousands km"
  }


  object BMWwithMileage {

    object F15 extends CarWithMileage("F15", 309, mileage = mileageByOdometer)

    def mileageByOdometer = 25 // this is a mock, some complicated logic could be here
    def mileageByDiagnostics = 27
  }

  object VolvoWithMileage {

    object XC60 extends CarWithMileage("XC60", 250, mileage = mileageByDiagnostics)

    def mileageByOdometer = 20

    def mileageByDiagnostics = 23
  }

}
