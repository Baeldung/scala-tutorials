package com.baeldung.scala.classcompositionwithtraits

import com.baeldung.scala.classcompositionwithtraits.CarTraits._
import org.scalatest.matchers.should.Matchers; import org.scalatest.wordspec.AnyWordSpec

/**
 * @author Sergey Ionin
 */

class CarTraitsUnitTest extends AnyWordSpec with Matchers {

  "Class that extends Car" should {
    "inherit abstract class fields" in {
      val bmw = new BMW0("F15", 309) with Printable
      bmw.horsePower shouldBe 309
      bmw.model shouldBe "F15"
      bmw.print() shouldBe s"the model ${bmw.model} has ${bmw.horsePower} HP under the hood. "
    }
  }

  "Objects that extends Car" should {
    "inherit abstract class fields and methods" in {
      val bmwX7 = BMW.X7()
      bmwX7.horsePower shouldBe 335
      bmwX7.model shouldBe "X7"
      bmwX7.print() shouldBe s"the model ${bmwX7.model} has ${bmwX7.horsePower} HP under the hood. "
    }
  }

  "Classes that extends Car with SimpleMarshaller" should {
    "inherit abstract class fields and methods" +
      "and be marshallable" in {
      val bmw0 = new BMW0("F15", 309) with SimpleMarshaller
      bmw0.toJson shouldBe "{\"model\":F15,\n\"horsePower\":309}"
    }
  }

  "Classes that extends Car with Marshaller" should {
    "inherit abstract class fields and methods" +
      "and be marshallable" in {
      val bmw0 = new BMW0("F15", 309) with Marshaller
      bmw0.toJson shouldBe "{\"model\":F15,\n\"horsePower\":309}"
    }
  }

  // in this case the print method pf the rightmost trait calls the print method of the previously mixed trait (
  // see super.print... in the implementation)
  "Classes that extends Car with PrettyPrintable with ShortPrintable" should {
    "behave differently depending from the mixing order" in {
      val bmwPrintable1 = new BMWPrintable("F15", 309) with PrettyPrintable with ShortPrintable
      val bmwPrintable2 = new BMWPrintable("F15", 309) with ShortPrintable with PrettyPrintable
      bmwPrintable1.printInfo() shouldBe s"the model ${bmwPrintable1.model} has ${bmwPrintable1.horsePower}" +
        s" HP under the hood. You'll definitelly enjoy driving!"
      bmwPrintable2.printInfo() shouldBe s"the model ${bmwPrintable1.model} has ${bmwPrintable1.horsePower} " +
        s"HP under the hood. "
    }
  }

}
