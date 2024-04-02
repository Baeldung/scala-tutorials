package com.baeldung.scala.factorial

import com.baeldung.scala.factorial.FactorialCalculation.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

class FactorialCalculationUnitTest
  extends AnyFlatSpec
  with TableDrivenPropertyChecks
  with Matchers {

  private val fns = Seq(
    ("recursion", factorialUsingRecursion),
    ("tail recursion", factorialUsingTailRecursion),
    ("product", factorialUsingProduct),
    ("reduce", factorialUsingReduce)
  )

  private val table = Table(
    ("Number", "Factorial"),
    (0, BigInt(1)),
    (1, BigInt(1)),
    (2, BigInt(2)),
    (8, BigInt(40320)),
    (30, BigInt("265252859812191058636308480000000"))
  )

  fns.foreach { (name, fn) =>
    it should s"Calculate the factorial of a number using $name" in {
      forAll(table) { (num, expectedFactorial) =>
        fn(num) shouldBe expectedFactorial
      }
    }
  }

}
