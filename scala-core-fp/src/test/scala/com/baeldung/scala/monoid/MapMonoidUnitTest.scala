package com.baeldung.scala.monoid

import org.scalatest._
import org.scalatest.wordspec.AnyWordSpec

class MapMonoidUnitTest extends AnyWordSpec {

  "A Monoid type class" should {
    "combine two Lists using ListMonoidInstance" in {
      import ListMonoidInstance._
      implicit val monoid: Monoid[List[Int]] = listMonoid[Int]
      monoid.op(List(1, 2, 3), List(4, 5)) === List(1, 2, 3, 4, 5)
    }
    "combine two maps using MapMonoidInstance" in {
      import MapMonoidInstance._
      implicit val monoid: Monoid[Map[String, Int]] = mapMonoid[String, Int]
      val computed: Map[String, Int] = monoid.op(
        Map("one-or-ten" -> 1, "two" -> 2, "three" -> 3),
        Map("four" -> 4, "one-or-ten" -> 9)
      )

      /** uncomment if you want to see the results computed.keys.foreach(k =>
        * println(s"key: $k, value: ${computed(k)}"))
        */
      val actual =
        Map("one-or-ten" -> 10, "two" -> 2, "three" -> 3, "four" -> 4)
      assert(computed === actual)
    }
  }
}
