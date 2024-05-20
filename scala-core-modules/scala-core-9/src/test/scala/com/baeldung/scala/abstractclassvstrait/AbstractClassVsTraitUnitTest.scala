package com.baeldung.scala.abstractclassvstrait

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

import com.baeldung.scala.abstractclassvstrait.*

class AbstractClassVsTraitUnitTest
  extends AnyFlatSpec
  with TableDrivenPropertyChecks
  with Matchers {

  private val calculator = Table(
    "term" -> "output",
    (Add(Literal(1), Multiply(Literal(2), Literal(3))), 7),
    (Add(Literal(20), Literal(20)), 40),
    (Multiply(Literal(20), Literal(20)), 400),
    (Literal(5), 5)
  )

  it should s"validate calculator interpreter" in {
    forAll(calculator) { (term, expectedResult) =>
      Interpreter.interpret(new Calculator, term) shouldBe expectedResult
    }
  }

  private val adder = Table(
    "nums" -> "output",
    (List(1, 2, 3, 4), 10),
    (List(20, 20), 40)
  )

  it should s"validate adder interpreter" in {
    forAll(adder) { (term, expectedResult) =>
      Interpreter.interpret(new Adder, term) shouldBe expectedResult
    }
  }

  private val multiplier = Table(
    "nums" -> "output",
    (List(1, 2, 3, 4), 24),
    (List(20, 20), 400)
  )

  it should s"validate multiplier interpreter" in {
    forAll(multiplier) { (term, expectedResult) =>
      Interpreter.interpret(new Multiplier, term) shouldBe expectedResult
    }
  }
}
