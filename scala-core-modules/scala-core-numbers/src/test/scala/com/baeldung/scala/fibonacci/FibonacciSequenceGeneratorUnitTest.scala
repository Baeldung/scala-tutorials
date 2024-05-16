package com.baeldung.scala.fibonacci

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import FibonacciSequenceGenerator._

class FibonacciSequenceGeneratorUnitTest
  extends AnyFlatSpec
  with Matchers
  with TableDrivenPropertyChecks {

  private val fns = List(
    ("Recursion", fibSequenceRecursion),
    ("Tail Recursion", fibTailRec),
    ("LazyList", fibLazyList),
    ("Iterator", fibIterator)
  )

  private val fibTables = Table(
    ("fib limit", "sequence"),
    (3, List(0L, 1L, 1L)),
    (-5, List()),
    (0, List()),
    (1, List(0L)),
    (2, List(0L, 1L)),
    (6, List(0L, 1L, 1L, 2L, 3L, 5L))
  )

  fns.foreach { (name, fn) =>
    it should s"[$name] generate fibonacci sequence correctly for each implementations" in {
      forAll(fibTables) { (num, exp) =>
        withClue(s"Executing function ${name} for input $num") {
          fn(num) shouldBe exp
        }
      }
    }
  }
}
