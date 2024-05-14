package com.baeldung.scala.functioncomposition

import org.scalatest.wordspec.AnyWordSpec

class FunctionCompositionUnitTest extends AnyWordSpec {

  "f andThen g" should {
    "apply first f and then g" in {
      val f = (x: Float) => x.abs
      val g = (x: Float) => x + 3
      val h1 = f andThen g
      val h2 = g andThen f

      assert(h1(-1) == 4f)
      assert(h2(-1) == 2f)
    }
  }

  "f compose g" should {
    "apply first g and then f" in {
      val f = (x: Float) => x.abs
      val g = (x: Float) => x + 3
      val h1 = f compose g
      val h2 = g compose f

      assert(h1(-1) == 2f)
      assert(h2(-1) == 4f)
    }
  }

  "binary functions" should {
    "compose when reduced to unary functions" in {
      val f = (x: Int, y: Int) => (x + 1, y + 2)
      val g = (x: Int, y: Int) => x - y
      val h = f.tupled andThen g.tupled

      val ftupl = (t: (Int, Int)) => (t._1 + 1, t._2 + 2)
      val gtupl = (t: (Int, Int)) => t._1 - t._2
      val htupl = ftupl andThen gtupl

      assert(h((5, 4)) == 0)
      assert(htupl((5, 4)) == 0)
    }

    "compose when reduced to unary functions and only one is binary" in {
      val f1 = (x: Int, y: Int) => x + y
      val g1 = (x: Int) => x + 1
      val h1 = f1.tupled andThen g1
      assert(h1((5, 4)) == 10)

      val f2 = (x: Int) => (x + 1, x - 1)
      val g2 = (x: Int, y: Int) => x * y
      val h2 = f2 andThen g2.tupled
      assert(h2(2) == 3)
    }
  }
}
