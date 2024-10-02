package com.baeldung.scala.listsum

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

class SumListUnitTest
  extends AnyFlatSpec
  with Matchers
  with TableDrivenPropertyChecks {

  private val table = Table(
    ("list", "expected sum"),
    (List(1, 2, 3, 4), 10),
    (List(0), 0),
    (List.empty[Int], 0)
  )

  private val fns = List(
    ("sum()", SumList.sum),
    ("sumByFold()", SumList.sumByFold),
    ("sumByReduce()", SumList.sumByReduce),
    ("sumByIteration()", SumList.sumByIteration),
    ("sumByFor()", SumList.sumByFor),
    ("sumByTailRecursion()", SumList.sumByTailRecursion),
  )

  it should "calculate sum for a list" in {
    forAll(table) { (input, expected) =>
      fns map { (name, fn) =>
        withClue(s"sum using the function `${name}` ") {
          fn(input) shouldBe expected
        }
      }
    }
  }

}
