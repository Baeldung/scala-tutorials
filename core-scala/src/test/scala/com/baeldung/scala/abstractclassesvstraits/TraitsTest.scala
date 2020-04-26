package com.baeldung.scala.abstractclassesvstraits

import org.scalatest.{Matchers, WordSpec}

class TraitsTest extends WordSpec with Matchers {

  import com.baeldung.scala.abstractclassesvstraits.CarTraits._

  "Class that extends Car" should {
    "inherit abstract class fields" in {
      val bmw = new BMW0("F15", 309)
      bmw.horsePower shouldBe 309
      bmw.model shouldBe "F15"
      bmw.print shouldBe s"the model ${bmw.model} has ${bmw.horsePower} HP under the hood"
    }
  }

  "Objects that extends Car" should {
    "inherit abstract class fields and methods" in {
      val bmwX7 = BMW.X7()
      bmwX7.horsePower shouldBe 335
      bmwX7.model shouldBe "X7"
      bmwX7.print shouldBe s"the model ${bmwX7.model} has ${bmwX7.horsePower} HP under the hood"
    }
  }

  "Classes that extends Car with Marshaller" should {
    "inherit abstract class fields and methods" +
      "and be marshallable" in {
      val bmw0 = new BMW0("F15", 309) with Marshaller
      bmw0.toJson shouldBe "{\"model\":F15,\n\"horsePower\":309}"
    }
  }

}
