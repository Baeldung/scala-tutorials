package com.baeldung.scala.absClassAndtraits

import com.baeldung.scala.absClassAndtraits.TutorialHelper.{BirdLikeEmployee, Employee, SingerAndFlierEmployee, SuperWomanLikeEmployee}
import org.scalatest.{Matchers, WordSpec}

class AbstractClassAndTraitsTogetherTest extends WordSpec with Matchers {

  val employeeFromDublin =
    new Employee(1001,"Robert McCullam",50,"Dublin","Cork",12)

  val employeeFromBucharest =
    new Employee(1002,"Giorghe Hagi",60,"Bucharest","HQ",15)

  val employeeFromCalcutta =
    new Employee(1003,"Amartya Sen",60,"Calcutta","London",9)

  val flierEmployeeFromDublin =
    new BirdLikeEmployee(1004,"Paul Crowley",50,"Dublin","Cork",12)

  val superWomandFromCalcutta =
    new SuperWomanLikeEmployee(1005,"Tamanna Sengupta",60,"Calcutta","London",9)

  val manySkilledEmployeeFromMunich =
    new SingerAndFlierEmployee(1006,"Lothar Matheius",55,"Munich","Berlin",9)


  "An employee aged over 60 and working for more than 10 years " should {
    "be eligible for special allowance " in {
      employeeFromBucharest.isEligibleForSpecialAllowance shouldBe  true
    }
  }

  "An employee aged over 60 and working for less than 10 years " should {
    "not be eligible for special allowance " in {
      employeeFromCalcutta.isEligibleForSpecialAllowance shouldBe false
    }
  }

  "A flyer employee from Dublin " should {
    "have only 2 wings and fly normally " in {
        flierEmployeeFromDublin.minWings  shouldBe 2
        flierEmployeeFromDublin.extraWings  shouldBe 0
        flierEmployeeFromDublin.flyFor(10)  shouldBe 14
      }
    }

    "A superwoman employee from Calcutta " should {
      "have 2 regular wings, 2 extra wings and fly for longer " in {
        superWomandFromCalcutta.minWings  shouldBe 2
        superWomandFromCalcutta.extraWings  shouldBe 2
        superWomandFromCalcutta.flyFor(10)  shouldBe 400
      }
    }

    "A highly skilled employee from Munich " should {
      "have 2 regular wings, 2 extra wings and fly for longer " in {
        manySkilledEmployeeFromMunich.name  shouldBe "Lothar Matheius"
        manySkilledEmployeeFromMunich.isEligibleForSpecialAllowance shouldBe false
        manySkilledEmployeeFromMunich.minWings  shouldBe 4
        manySkilledEmployeeFromMunich.extraWings  shouldBe 2
        manySkilledEmployeeFromMunich.flyFor(10)  shouldBe 800
        manySkilledEmployeeFromMunich.requestASong("DoReMi")  shouldBe "www.youtube.com/bundleInOfficeLibrary.mp4"
      }
    }


  }
