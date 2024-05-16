package com.baeldung.scala.collections

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MapKeyValueUnitTest extends AnyWordSpec with Matchers {
  "Map::map" should {
    "map both keys and values to a new Iterable" in {
      val m = Map(1 -> "A", 2 -> "B")
      val f = { (t: (Int, String)) => s"${t._1}${t._2}" }
      (m map f) shouldBe Iterable("1A", "2B")
    }

    "map both keys and values to a new Iterable when the function is binary" in {
      val m = Map(1 -> "A", 2 -> "B")
      val f = { (k: Int, v: String) => s"$k$v" }
      (m map f.tupled) shouldBe Iterable("1A", "2B")
    }

    "map both keys and values to a new Map" in {
      val m: Map[Int, String] = Map(1 -> "A", 2 -> "BB")
      val newMap: Map[String, Int] = m map { case (k, v) =>
        (k.toString, v.length)
      }
      newMap shouldBe Map("1" -> 1, "2" -> 2)
    }

    "map both keys and values to a new Map and remove duplicates" in {
      val m: Map[Int, String] = Map(1 -> "A", 2 -> "BB")
      val newMap: Map[Int, Int] = m map { case (_, v) => (1, v.length) }
      newMap shouldBe Map(1 -> 2)
    }
  }

  "Map::flatMap" should {
    "map both keys and values to a new Iterable" in {
      val m = Map(1 -> "A", 2 -> "B", 3 -> "C")

      val newIterable: Iterable[String] = m flatMap { case (k, v) =>
        List.fill(k)(v)
      }
      newIterable shouldBe Iterable("A", "B", "B", "C", "C", "C")

      val newMap: Map[Int, String] = m flatMap { case (k, v) =>
        (1 to k).map(i => i -> s"$i$v")
      }
      newMap shouldBe Map(1 -> "1C", 2 -> "2C", 3 -> "3C")
    }
  }

  "Map::transform" should {
    "produce a new Map by taking the original keys into account" in {
      val m: Map[Int, Char] = Map(1 -> 'A', 2 -> 'B', 3 -> 'C')

      val newMap: Map[Int, String] = m transform { case (k, v) =>
        s"$k$v"
      }
      newMap shouldBe Map(1 -> "1A", 2 -> "2B", 3 -> "3C")
    }
  }
}
