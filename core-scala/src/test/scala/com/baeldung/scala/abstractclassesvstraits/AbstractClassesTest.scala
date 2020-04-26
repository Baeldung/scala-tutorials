package com.baeldung.scala.abstractclassesvstraits

import org.scalatest.{Matchers, WordSpec}

class AbstractClassesTest extends WordSpec with Matchers {

  import com.baeldung.scala.abstractclassesvstraits.CarAbstractClasses._

  "Class that extends Car0" should {
    "implement abstract class fields" in {
      val bmw0 = new BMW0("F15", 309)
      bmw0.horsePower shouldBe 309
      bmw0.model shouldBe "F15"
    }
  }

  "Classes that extends Car1" should {
    "implement abstract class fields and methods" in {
      val bmw1 = new BMW1("F15", 309)
      bmw1.horsePower shouldBe 309
      bmw1.model shouldBe "F15"
      bmw1.print shouldBe s"the model ${bmw1.model} has ${bmw1.horsePower} HP under the hood"

      val volvo1 = new Volvo1("XC60", 250)
      volvo1.horsePower shouldBe 250
      volvo1.model shouldBe "XC60"
      volvo1.print shouldBe s"the model ${volvo1.model} has ${volvo1.horsePower} HP under the hood"
    }
  }

  "Objects that extends Car2" should {
    "implement abstract class fields and methods" in {
      val bmwX7 = BMW.X7
      bmwX7.horsePower shouldBe 335
      bmwX7.model shouldBe "X7"
      bmwX7.print shouldBe s"the model ${bmwX7.model} has ${bmwX7.horsePower} HP under the hood"

      val volvoXC90 = Volvo.XC90
      volvoXC90.horsePower shouldBe 185
      volvoXC90.model shouldBe "XC90"
      volvoXC90.print shouldBe s"the model ${volvoXC90.model} has ${volvoXC90.horsePower} HP under the hood"
    }
  }

  "Objects that extends CarWithMileage " should {
    "be able to provide mileage information" in {
      val bmwF15 = BMWwithMileage.F15
      bmwF15.horsePower shouldBe 309
      bmwF15.model shouldBe "F15"
      bmwF15.printMileage shouldBe s"the model ${bmwF15.model} has mileage of" +
        s" ${BMWwithMileage.mileageByOdometer} thousands km"

      val volvoXC60 = VolvoWithMileage.XC60
      volvoXC60.horsePower shouldBe 250
      volvoXC60.model shouldBe "XC60"
      volvoXC60.printMileage shouldBe s"the model ${volvoXC60.model} has mileage of" +
        s" ${VolvoWithMileage.mileageByDiagnostics} thousands km"
    }
  }

}
