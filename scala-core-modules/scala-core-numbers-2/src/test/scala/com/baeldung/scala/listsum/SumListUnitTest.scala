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
    ("sumByTailRecursion()", SumList.sumByTailRecursion)
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

  it should "calculate sum for any numeric types" in {
    val ints = List(1, 2, 3, 4)
    SumList.sumNumeric(ints) shouldBe 10
    SumList.sumNumeric(List.empty[Int]) shouldBe 0

    val doubles = List(1d, 2d, 3d, 4d)
    SumList.sumNumeric(doubles) shouldBe 10d

    val bigints = List(BigInt(1), BigInt(2), BigInt(3), BigInt(4))
    SumList.sumNumeric(bigints) shouldBe BigInt(10)

  }

  it should "calculate the sum of inside fields" in {
    case class Item(price: Int)
    val items = List(Item(1), Item(2), Item(3), Item(4))
    items.foldLeft(0)((acc, item) => acc + item.price) shouldBe 10
  }

}
