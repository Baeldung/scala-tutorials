package com.baeldung.scala.maps

import java.util.concurrent.atomic.AtomicInteger

import org.scalatest.{Matchers, WordSpec}

import scala.collection.immutable.ListMap
import scala.collection.{SortedMap, immutable, mutable}

class MapsUnitTest extends WordSpec with Matchers {

  "immutable.Map" should {
    val map: Map[Int, String] = immutable.Map((1, "first"), 2 -> "second")
    "put key -> value" in {
      val newMap = map + (3 -> "third")

      newMap.get(3) shouldBe Some("third")
    }
    "put multiple key -> value" in {
      val newMap = map + (3 -> "third", 4 -> "4th")

      newMap.get(3) shouldBe Some("third")
      newMap.get(4) shouldBe Some("4th")
    }
    "combine with Map" in {
      val newMap = map ++ Map(3 -> "third", 4 -> "4th")

      newMap.get(3) shouldBe Some("third")
      newMap.get(4) shouldBe Some("4th")
    }
    "combine with List of tuples" in {
      val newMap = map ++ List(3 -> "third", 4 -> "4th")

      newMap.get(3) shouldBe Some("third")
      newMap.get(4) shouldBe Some("4th")
    }
    "override value for existing key" in {
      val newMap = map + (1 -> "1st")

      newMap.get(1) shouldBe Some("1st")
    }
    "get element by key in safe way" in {
      map.get(1) shouldBe Some("first")
      map.get(3) shouldBe None
    }
    "get element with default by key in safe way" in {
      map.getOrElse(1, "zero") shouldBe "first"
      map.getOrElse(3, "zero") shouldBe "zero"
    }
    "get element by key in unsafe way" in {
      map.apply(1) shouldBe "first"
      map(2) shouldBe "second"
      the[NoSuchElementException] thrownBy map(3)
    }
    "get default value for missing key" in {
      val mapWithDefault = map.withDefaultValue("none")
      mapWithDefault(3) shouldBe "none"
    }
    "get default value computed from missing key" in {
      val mapWithDefault = map.withDefault(i => i + "th")
      mapWithDefault(5) shouldBe "5th"
    }
    "get keys" in {
      map.keys should contain allOf (1, 2)
      map.keySet should contain allOf (1, 2)
    }
    "get values" in {
      map.values should contain allOf ("first", "second")
    }
    "map values" in {
      val count = new AtomicInteger(0)
      val reversed = map.mapValues { s =>
        count.incrementAndGet()
        s.reverse
      }

      reversed.get(1) shouldBe Some("tsrif")
      reversed.get(1) shouldBe Some("tsrif")
      reversed.get(3) shouldBe None
      count.get() shouldBe 2
    }
    "filter keys" in {
      val count = new AtomicInteger(0)
      val reversed = map.filterKeys { s =>
        count.incrementAndGet()
        s > 1
      }

      reversed.get(1) shouldBe None
      reversed.get(2) shouldBe Some("second")
      reversed.get(3) shouldBe None
      count.get() shouldBe 3
    }
    "remove key" in {
      val newMap = map - 1

      newMap.get(1) shouldBe None
      newMap.get(2) shouldBe Some("second")
    }
    "remove multiple keys" in {
      val newMap = map - (1, 2)

      newMap.get(1) shouldBe None
      newMap.get(2) shouldBe None
    }
    "remove list of  keys" in {
      val newMap = map -- List(1, 2)

      newMap.get(1) shouldBe None
      newMap.get(2) shouldBe None
    }
    "sort by values" in {
      implicit val ordering: Ordering[String] = Ordering.by(_.length)

      val sortedMap = map.toList.sortBy { case (_, value) => value }.toMap

      sortedMap.toList shouldBe List(1 -> "first", 2 -> "second")
    }
  }

  "mutable.Map" should {
    "put key -> value" in {
      val map: mutable.Map[Int, String] =
        mutable.Map((1, "first"), 2 -> "second")

      val newMap = map + (3 -> "third")
      map += (3 -> "third")

      map.get(3) shouldBe Some("third")
      newMap.get(3) shouldBe Some("third")
    }
    "put multiple key -> value" in {
      val map: mutable.Map[Int, String] =
        mutable.Map((1, "first"), 2 -> "second")

      val newMap = map + (3 -> "third", 4 -> "4th")
      map += (3 -> "third", 4 -> "4th")

      map.get(3) shouldBe Some("third")
      map.get(4) shouldBe Some("4th")
      newMap.get(3) shouldBe Some("third")
      newMap.get(4) shouldBe Some("4th")
    }
    "combine with Map" in {
      val map: mutable.Map[Int, String] =
        mutable.Map((1, "first"), 2 -> "second")

      val newMap = map ++ Map(3 -> "third", 4 -> "4th")
      map ++= Map(3 -> "third", 4 -> "4th")

      map.get(3) shouldBe Some("third")
      map.get(4) shouldBe Some("4th")
      newMap.get(3) shouldBe Some("third")
      newMap.get(4) shouldBe Some("4th")
    }
    "combine with List of tuples" in {
      val map: mutable.Map[Int, String] =
        mutable.Map((1, "first"), 2 -> "second")

      val newMap = map ++ List(3 -> "third", 4 -> "4th")
      map ++= List(3 -> "third", 4 -> "4th")

      map.get(3) shouldBe Some("third")
      map.get(4) shouldBe Some("4th")
      newMap.get(3) shouldBe Some("third")
      newMap.get(4) shouldBe Some("4th")
    }
    "override value for existing key" in {
      val map: mutable.Map[Int, String] =
        mutable.Map((1, "first"), 2 -> "second")

      val newMap = map + (1 -> "1st")
      map += (1 -> "1st")

      map.get(1) shouldBe Some("1st")
      newMap.get(1) shouldBe Some("1st")
    }
    "get element by key in safe way" in {
      val map: mutable.Map[Int, String] =
        mutable.Map((1, "first"), 2 -> "second")

      map.get(1) shouldBe Some("first")
      map.get(3) shouldBe None
    }
    "get element with default by key in safe way" in {
      val map: mutable.Map[Int, String] =
        mutable.Map((1, "first"), 2 -> "second")

      map.getOrElse(1, "zero") shouldBe "first"
      map.getOrElse(3, "zero") shouldBe "zero"
    }
    "get element by key in unsafe way" in {
      val map: mutable.Map[Int, String] =
        mutable.Map((1, "first"), 2 -> "second")

      map.apply(1) shouldBe "first"
      map(2) shouldBe "second"
      the[NoSuchElementException] thrownBy map(3)
    }
    "get default value for missing key" in {
      val map: mutable.Map[Int, String] =
        mutable.Map((1, "first"), 2 -> "second")

      val mapWithDefault = map.withDefaultValue("none")

      mapWithDefault(3) shouldBe "none"
    }
    "get default value computed from missing key" in {
      val map: mutable.Map[Int, String] =
        mutable.Map((1, "first"), 2 -> "second")

      val mapWithDefault = map.withDefault(i => i + "th")

      mapWithDefault(5) shouldBe "5th"
    }
    "get keys" in {
      val map: mutable.Map[Int, String] =
        mutable.Map((1, "first"), 2 -> "second")

      map.keys should contain allOf (1, 2)
      map.keySet should contain allOf (1, 2)
    }
    "get values" in {
      val map: mutable.Map[Int, String] =
        mutable.Map((1, "first"), 2 -> "second")

      map.values should contain allOf ("first", "second")
    }
    "map values" in {
      val map: mutable.Map[Int, String] =
        mutable.Map((1, "first"), 2 -> "second")

      val count = new AtomicInteger(0)
      val reversed = map.mapValues { s =>
        count.incrementAndGet()
        s.reverse
      }

      reversed.get(1) shouldBe Some("tsrif")
      reversed.get(1) shouldBe Some("tsrif")
      reversed.get(3) shouldBe None
      count.get() shouldBe 2
    }
    "filter keys" in {
      val map: mutable.Map[Int, String] =
        mutable.Map((1, "first"), 2 -> "second")

      val count = new AtomicInteger(0)
      val reversed = map.filterKeys { s =>
        count.incrementAndGet()
        s > 1
      }

      reversed.get(1) shouldBe None
      reversed.get(2) shouldBe Some("second")
      reversed.get(3) shouldBe None
      count.get() shouldBe 3
    }
    "remove key" in {
      val map: mutable.Map[Int, String] =
        mutable.Map((1, "first"), 2 -> "second")

      val newMap = map - 1
      map -= 1

      map.get(1) shouldBe None
      map.get(2) shouldBe Some("second")
      newMap.get(1) shouldBe None
      newMap.get(2) shouldBe Some("second")
    }
    "remove multiple keys" in {
      val map: mutable.Map[Int, String] =
        mutable.Map((1, "first"), 2 -> "second")

      val newMap = map - (1, 2)
      map -= (1, 2)

      map.get(1) shouldBe None
      map.get(2) shouldBe None
      newMap.get(1) shouldBe None
      newMap.get(2) shouldBe None
    }
    "remove list of  keys" in {
      val map: mutable.Map[Int, String] =
        mutable.Map((1, "first"), 2 -> "second")

      val newMap = map -- List(1, 2)
      map --= List(1, 2)

      map.get(1) shouldBe None
      map.get(2) shouldBe None
      newMap.get(1) shouldBe None
      newMap.get(2) shouldBe None
    }
  }
  "ListMap" should {
    "keep order of values" in {
      val map = ListMap(2 -> "second", 1 -> "first") + (4 -> "4th")

      map.values.toList shouldBe List("second", "first", "4th")
    }
    "keep order of keys" in {
      val map = ListMap(2 -> "second", 1 -> "first") + (4 -> "4th")

      map.keys.toList shouldBe List(2, 1, 4)
    }
  }
  "SortedMap" should {
    "keep elements sorted" in {
      val map = SortedMap(2 -> "second", 1 -> "first") + (4 -> "4th")

      map.toList shouldBe List(1 -> "first", 2 -> "second", 4 -> "4th")
    }
    "keep element sorted by given ordering" in {
      implicit val customOrdering: Ordering[Int] = Ordering.Int.reverse

      val map = SortedMap(2 -> "second", 1 -> "first") + (4 -> "4th")

      map.toList shouldBe List(
        4 -> "4th",
        2 -> "second",
        1 -> "first"
      )
    }
  }
}
