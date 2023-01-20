//package com.baeldung.scala.maps
//
//import java.util.concurrent.atomic.AtomicInteger
//import org.scalatest.{Matchers, WordSpec}
//
//import scala.collection.immutable.ListMap
//import scala.collection.{MapView, SortedMap, immutable, mutable}
//
//class MapsUnitTest extends WordSpec with Matchers {
//
//  "immutable.Map" should {
//    val map: Map[Int, String] = immutable.Map((1, "first"), 2 -> "second")
//
//    "create empty map with empty method" in {
//      val map: Map[Int, String] = immutable.Map.empty[Int, String]
//      map shouldBe empty
//    }
//    "create non-empty map" in {
//      val map: Map[Int, String] = immutable.Map((1, "first"), 2 -> "second")
//      map should not be empty
//    }
//    "create empty map with ()" in {
//      val map: Map[Int, String] = immutable.Map[Int, String]()
//      map shouldBe empty
//
//    }
//    "fold list into map" in {
//      val map: Map[Int, String] = List(1 -> "first", 2 -> "second")
//        .foldLeft(Map.empty[Int, String]) {
//          case (map, (key, value)) =>
//            map + (key -> value)
//        }
//
//      map shouldBe Map(1 -> "first", 2 -> "second")
//    }
//    "create map from List" in {
//      val map: Map[Int, String] = List(1 -> "first", 2 -> "second").toMap
//      map shouldBe Map(1 -> "first", 2 -> "second")
//    }
//    "put key -> value" in {
//      val initialMap: Map[Int, String] = Map(1 -> "first")
//
//      val newMap: Map[Int, String] = initialMap + (2 -> "second")
//
//      initialMap shouldBe Map(1 -> "first")
//      newMap shouldBe Map(1 -> "first", 2 -> "second")
//    }
//    "put multiple key -> value" in {
//      val initialMap: Map[Int, String] = Map(1 -> "first")
//
//      val newMap: Map[Int, String] = initialMap + (2 -> "second", 3 -> "third")
//
//      initialMap shouldBe Map(1 -> "first")
//      newMap shouldBe Map(1 -> "first", 2 -> "second", 3 -> "third")
//    }
//    "combine with Map" in {
//      val leftMap: Map[Int, String] = Map(1 -> "first", 2 -> "second")
//      val rightMap: Map[Int, String] = Map(2 -> "2nd", 3 -> "third")
//
//      val map = leftMap ++ rightMap
//
//      map shouldBe Map(1 -> "first", 2 -> "2nd", 3 -> "third")
//    }
//    "combine with List of tuples" in {
//      val leftMap: Map[Int, String] = Map(1 -> "first", 2 -> "second")
//      val list: List[(Int, String)] = List(2 -> "2nd", 3 -> "third")
//
//      val map = leftMap ++ list
//
//      map shouldBe Map(1 -> "first", 2 -> "2nd", 3 -> "third")
//    }
//    "override value for existing key" in {
//      val initialMap: Map[Int, String] = Map(1 -> "first")
//
//      val newMap = initialMap + (1 -> "1st")
//
//      initialMap shouldBe Map(1 -> "first")
//      newMap shouldBe Map(1 -> "1st")
//    }
//    "get element by key in safe way" in {
//      val map: Map[Int, String] = Map(1 -> "first", 2 -> "second")
//
//      map.get(1) shouldBe Some("first")
//      map.get(3) shouldBe None
//    }
//    "get element with default by key in safe way" in {
//      map.getOrElse(1, "zero") shouldBe "first"
//      map.getOrElse(3, "zero") shouldBe "zero"
//    }
//    "get element by key in unsafe way" in {
//      val map: Map[Int, String] = Map(1 -> "first", 2 -> "second")
//
//      map(1) shouldBe "first"
//      the[NoSuchElementException] thrownBy map(3)
//    }
//    "get default value for missing key" in {
//      val map: Map[Int, String] = Map(1 -> "first", 2 -> "second")
//
//      val mapWithDefault: Map[Int, String] = map.withDefaultValue("unknown")
//
//      mapWithDefault(1) shouldBe "first"
//      mapWithDefault(3) shouldBe "unknown"
//
//    }
//    "get default value computed from missing key" in {
//      val map: Map[Int, String] = Map(1 -> "first", 2 -> "second")
//
//      val mapWithDefault: Map[Int, String] = map.withDefault(i => i + "th")
//
//      mapWithDefault(1) shouldBe "first"
//      mapWithDefault(5) shouldBe "5th"
//    }
//    "get keys" in {
//      map.keys should contain allOf (1, 2)
//      map.keySet should contain allOf (1, 2)
//    }
//    "get values" in {
//      map.values should contain allOf ("first", "second")
//    }
//    "map" in {
//      val initialMap: Map[Int, String] = Map(1 -> "first", 2 -> "second")
//
//      val abbreviate: ((Int, String)) => (Int, String) = {
//        case (key, value) =>
//          val newValue = key + value.takeRight(2)
//          key -> newValue
//      }
//
//      val abbreviatedMap = initialMap.map(abbreviate)
//
//      initialMap shouldBe Map(1 -> "first", 2 -> "second")
//      abbreviatedMap shouldBe Map(1 -> "1st", 2 -> "2nd")
//    }
//    "map values with counter" in {
//      val initialMap: Map[Int, String] = Map(1 -> "first", 2 -> "second")
//
//      val counter = new AtomicInteger(0)
//      val reverse: String => String = { value =>
//        counter.incrementAndGet()
//        value.reverse
//      }
//
//      val reversed = initialMap.view.mapValues(reverse)
//
//      counter.get() shouldBe 0
//
//      reversed.get(1) shouldBe Some("tsrif")
//      counter.get() shouldBe 1
//
//      reversed.get(2) shouldBe Some("dnoces")
//      counter.get() shouldBe 2
//
//      reversed.get(1) shouldBe Some("tsrif")
//      counter.get() shouldBe 3
//    }
//    "map values" in {
//      val initialMap: Map[Int, String] = Map(1 -> "first", 2 -> "second")
//
//      val reverse: String => String = value => value.reverse
//
//      val reversed: Map[Int, String] = initialMap.view.mapValues(reverse).toMap
//
//      reversed.get(1) shouldBe Some("tsrif")
//      reversed.get(2) shouldBe Some("dnoces")
//    }
//    "map values force" in {
//      val initialMap: Map[Int, String] = Map(1 -> "first", 2 -> "second")
//
//      val counter = new AtomicInteger(0)
//      val reverse: String => String = { value =>
//        counter.incrementAndGet()
//        value.reverse
//      }
//
//      //todo: force is no more supported, better to remove from article?
//      val reversed: Map[Int, String] = initialMap
//        .mapValues(reverse)
//        .view
//        .force.toMap
//
//      counter.get() shouldBe map.size
//
//      reversed.get(1) shouldBe Some("tsrif")
//      counter.get() shouldBe map.size
//
//      reversed.get(2) shouldBe Some("dnoces")
//      counter.get() shouldBe map.size
//    }
//
//    "filter keys with counter" in {
//      val initialMap: Map[Int, String] = Map(1 -> "first", 2 -> "second")
//
//      val counter = new AtomicInteger(0)
//      val predicate: Int => Boolean = { key =>
//        counter.incrementAndGet()
//        key > 1
//      }
//      val filtered: Map[Int, String] = initialMap.view.filterKeys(predicate).toMap
//
//      counter.get() shouldBe 0
//
//      filtered.get(1) shouldBe None
//      counter.get() shouldBe 1
//      filtered.get(2) shouldBe Some("second")
//      counter.get() shouldBe 2
//    }
//    "filter keys strict with counter" in {
//      val initialMap: Map[Int, String] = Map(1 -> "first", 2 -> "second")
//
//      val counter = new AtomicInteger(0)
//      val predicate: Int => Boolean = { key =>
//        counter.incrementAndGet()
//        key > 1
//      }
//      //todo: force is no more supported, better to remove from article?
//      val filtered: Map[Int, String] =
//        initialMap.view.filterKeys(predicate).view.force.toMap
//
//      counter.get() shouldBe initialMap.size
//
//      filtered.get(1) shouldBe None
//      counter.get() shouldBe initialMap.size
//      filtered.get(2) shouldBe Some("second")
//      counter.get() shouldBe initialMap.size
//    }
//
//    "filter" in {
//      val predicate: ((Int, String)) => Boolean = {
//        case (key, value) => key > 1 && value.length > 5
//      }
//
//      val initialMap: Map[Int, String] = Map(1 -> "first", 2 -> "second")
//
//      val filtered: Map[Int, String] = initialMap.filter(predicate)
//
//      filtered shouldBe Map(2 -> "second")
//    }
//
//    "filter keys" in {
//      val predicate: Int => Boolean = key => key > 1
//
//      val initialMap: Map[Int, String] = Map(1 -> "first", 2 -> "second")
//
//      val filtered: Map[Int, String] = initialMap.view.filterKeys(predicate).toMap
//
//      filtered.get(1) shouldBe None
//      filtered.get(2) shouldBe Some("second")
//    }
//    "remove key" in {
//      val initialMap: Map[Int, String] = Map(1 -> "first", 2 -> "second")
//
//      val newMap: Map[Int, String] = initialMap - 1
//
//      initialMap shouldBe Map(1 -> "first", 2 -> "second")
//      newMap shouldBe Map(2 -> "second")
//    }
//    "remove multiple keys" in {
//      val initialMap: Map[Int, String] = Map(1 -> "first", 2 -> "second")
//
//      val newMap: Map[Int, String] = initialMap - (1, 2, 3)
//
//      initialMap shouldBe Map(1 -> "first", 2 -> "second")
//      newMap shouldBe empty
//    }
//    "remove list of  keys" in {
//      val initialMap: Map[Int, String] = Map(1 -> "first", 2 -> "second")
//
//      val newMap: Map[Int, String] = map -- List(1, 2)
//
//      initialMap shouldBe Map(1 -> "first", 2 -> "second")
//      newMap shouldBe empty
//    }
//    "sort by values" in {
//      implicit val ordering: Ordering[String] = Ordering.by(_.length)
//
//      val sortedMap =
//        ListMap(map.toList.sortBy { case (_, value) => value }: _*)
//
//      sortedMap shouldBe ListMap(1 -> "first", 2 -> "second")
//    }
//  }
//
//  "mutable.Map" should {
//    "put key -> value" in {
//      val map: mutable.Map[Int, String] =
//        mutable.Map((1, "first"), 2 -> "second")
//
//      val newMap = map + (3 -> "third")
//      map += (3 -> "third")
//
//      map.get(3) shouldBe Some("third")
//      newMap.get(3) shouldBe Some("third")
//    }
//    "put multiple key -> value" in {
//      val map: mutable.Map[Int, String] =
//        mutable.Map((1, "first"), 2 -> "second")
//
//      val newMap = map + (3 -> "third", 4 -> "4th")
//      map += (3 -> "third", 4 -> "4th")
//
//      map.get(3) shouldBe Some("third")
//      map.get(4) shouldBe Some("4th")
//      newMap.get(3) shouldBe Some("third")
//      newMap.get(4) shouldBe Some("4th")
//    }
//    "combine with Map" in {
//      val map: mutable.Map[Int, String] =
//        mutable.Map((1, "first"), 2 -> "second")
//
//      val newMap = map ++ Map(3 -> "third", 4 -> "4th")
//      map ++= Map(3 -> "third", 4 -> "4th")
//
//      map.get(3) shouldBe Some("third")
//      map.get(4) shouldBe Some("4th")
//      newMap.get(3) shouldBe Some("third")
//      newMap.get(4) shouldBe Some("4th")
//    }
//    "combine with List of tuples" in {
//      val map: mutable.Map[Int, String] =
//        mutable.Map((1, "first"), 2 -> "second")
//
//      val newMap = map ++ List(3 -> "third", 4 -> "4th")
//      map ++= List(3 -> "third", 4 -> "4th")
//
//      map.get(3) shouldBe Some("third")
//      map.get(4) shouldBe Some("4th")
//      newMap.get(3) shouldBe Some("third")
//      newMap.get(4) shouldBe Some("4th")
//    }
//    "override value for existing key" in {
//      val map: mutable.Map[Int, String] =
//        mutable.Map((1, "first"), 2 -> "second")
//
//      val newMap = map + (1 -> "1st")
//      map += (1 -> "1st")
//
//      map.get(1) shouldBe Some("1st")
//      newMap.get(1) shouldBe Some("1st")
//    }
//    "get element by key in safe way" in {
//      val map: mutable.Map[Int, String] =
//        mutable.Map((1, "first"), 2 -> "second")
//
//      map.get(1) shouldBe Some("first")
//      map.get(3) shouldBe None
//    }
//    "get element with default by key in safe way" in {
//      val map: mutable.Map[Int, String] =
//        mutable.Map((1, "first"), 2 -> "second")
//
//      map.getOrElse(1, "zero") shouldBe "first"
//      map.getOrElse(3, "zero") shouldBe "zero"
//    }
//    "get element by key in unsafe way" in {
//      val map: mutable.Map[Int, String] =
//        mutable.Map((1, "first"), 2 -> "second")
//
//      map.apply(1) shouldBe "first"
//      map(2) shouldBe "second"
//      the[NoSuchElementException] thrownBy map(3)
//    }
//    "get default value for missing key" in {
//      val map: mutable.Map[Int, String] =
//        mutable.Map((1, "first"), 2 -> "second")
//
//      val mapWithDefault = map.withDefaultValue("none")
//
//      mapWithDefault(3) shouldBe "none"
//    }
//    "get default value computed from missing key" in {
//      val map: mutable.Map[Int, String] =
//        mutable.Map((1, "first"), 2 -> "second")
//
//      val mapWithDefault = map.withDefault(i => i + "th")
//
//      mapWithDefault(5) shouldBe "5th"
//    }
//    "get keys" in {
//      val map: mutable.Map[Int, String] =
//        mutable.Map((1, "first"), 2 -> "second")
//
//      map.keys should contain allOf (1, 2)
//      map.keySet should contain allOf (1, 2)
//    }
//    "get values" in {
//      val map: mutable.Map[Int, String] =
//        mutable.Map((1, "first"), 2 -> "second")
//
//      map.values should contain allOf ("first", "second")
//    }
//    "map values" in {
//      val map: mutable.Map[Int, String] =
//        mutable.Map((1, "first"), 2 -> "second")
//
//      val count = new AtomicInteger(0)
//      val reversed = map.mapValues { s =>
//        count.incrementAndGet()
//        s.reverse
//      }
//
//      reversed.get(1) shouldBe Some("tsrif")
//      reversed.get(1) shouldBe Some("tsrif")
//      reversed.get(3) shouldBe None
//      count.get() shouldBe 2
//    }
//    "filter keys" in {
//      val map: mutable.Map[Int, String] =
//        mutable.Map((1, "first"), 2 -> "second")
//
//      val count = new AtomicInteger(0)
//      val reversed = map.filterKeys { s =>
//        count.incrementAndGet()
//        s > 1
//      }
//
//      reversed.get(1) shouldBe None
//      reversed.get(2) shouldBe Some("second")
//      reversed.get(3) shouldBe None
//      count.get() shouldBe 3
//    }
//    "remove key" in {
//      val map: mutable.Map[Int, String] =
//        mutable.Map((1, "first"), 2 -> "second")
//
//      val newMap = map - 1
//      map -= 1
//
//      map.get(1) shouldBe None
//      map.get(2) shouldBe Some("second")
//      newMap.get(1) shouldBe None
//      newMap.get(2) shouldBe Some("second")
//    }
//    "remove multiple keys" in {
//      val map: mutable.Map[Int, String] =
//        mutable.Map((1, "first"), 2 -> "second")
//
//      val newMap = map - (1, 2)
//      map -= (1, 2)
//
//      map.get(1) shouldBe None
//      map.get(2) shouldBe None
//      newMap.get(1) shouldBe None
//      newMap.get(2) shouldBe None
//    }
//    "remove list of  keys" in {
//      val map: mutable.Map[Int, String] =
//        mutable.Map((1, "first"), 2 -> "second")
//
//      val newMap = map -- List(1, 2)
//      map --= List(1, 2)
//
//      map.get(1) shouldBe None
//      map.get(2) shouldBe None
//      newMap.get(1) shouldBe None
//      newMap.get(2) shouldBe None
//    }
//  }
//  "ListMap" should {
//    "keep order of values" in {
//      val map = ListMap(2 -> "second", 1 -> "first") + (4 -> "4th")
//
//      map.values.toList shouldBe List("second", "first", "4th")
//    }
//    "keep order of keys" in {
//      val map = ListMap(2 -> "second", 1 -> "first") + (4 -> "4th")
//
//      map.keys.toList shouldBe List(2, 1, 4)
//    }
//  }
//  "SortedMap" should {
//    "keep elements sorted" in {
//      val map = SortedMap(2 -> "second", 1 -> "first") + (4 -> "4th")
//
//      map.toList shouldBe List(1 -> "first", 2 -> "second", 4 -> "4th")
//    }
//    "keep element sorted by given ordering" in {
//      implicit val customOrdering: Ordering[Int] = Ordering.Int.reverse
//
//      val map = SortedMap(2 -> "second", 1 -> "first") + (4 -> "4th")
//
//      map.toList shouldBe List(
//        4 -> "4th",
//        2 -> "second",
//        1 -> "first"
//      )
//    }
//  }
//}
