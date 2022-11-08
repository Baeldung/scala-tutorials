package com.baeldung.scala.commoncollections

import org.scalatest.{FlatSpec, Matchers}

import scala.collection.immutable.ListMap

class ListMapOperationsUnitTest extends FlatSpec with Matchers {

  val countryCapitals: ListMap[String, String] = ListMap(
    "Canada" -> "Ottawa",
    "India" -> "Delhi",
    "Australia" -> "Canberra"
  );

  "ListMap.empty" should "create an empty ListMap" in {
    val listMap1: ListMap[Integer, Integer] = ListMap.empty
    val listMap2 = new ListMap();

    assert(listMap1.size == 0)
    assert(listMap2.size == 0)
  }

  "+ operator" should "add an entry to the ListMap" in {
    val newListMap = countryCapitals + ("Russia" -> "Moscow")

    assert(newListMap.size == 4)
    assert(
      newListMap.tail == ListMap(
        "India" -> "Delhi",
        "Australia" -> "Canberra",
        "Russia" -> "Moscow"
      )
    )
    assert(newListMap.head == ("Canada" -> "Ottawa"))
  }
  
  "+ operator" should "update the value of an existing key in the ListMap" in {
    assert(countryCapitals("India") == "Delhi")
    val newListMap = countryCapitals + ("India" -> "New Delhi")

    assert(newListMap.size == 3)
    assert(newListMap("India") == "New Delhi")
  }

  "- operator" should "remove an entry from the ListMap" in {
    val newListMap = countryCapitals - ("Russia");

    assert(newListMap.size == 3)
    assert(newListMap.last == ("Australia" -> "Canberra"))
    assert(newListMap.head == ("Canada" -> "Ottawa"))
  }

  "- operator" should "remove multiple entries from the ListMap" in {
    val newListMap = countryCapitals - ("India", "Australia", "Japan");

    assert(newListMap.size == 1)
    assert(newListMap.last == ("Canada" -> "Ottawa"))
    assert(newListMap.head == ("Canada" -> "Ottawa"))
  }

  "- operator" should "change nothing if a key is not present in the ListMap" in {
    var newListMap = countryCapitals - "someNonExistingKey";

    assert(newListMap.size == 3)
    assert(newListMap.last == ("Australia" -> "Canberra"))
    assert(newListMap.head == ("Canada" -> "Ottawa"))
  }

  "++ operator" should "combine two ListMaps" in {
    val OneMoreCountryCapitals = ListMap(
      "Cuba" -> "Havana",
      "France" -> "Paris",
      "Germany" -> "Berlin"
    );

    val newListMap = countryCapitals ++ OneMoreCountryCapitals;

    assert(newListMap.size == 6)
    assert(newListMap.last == ("Germany" -> "Berlin"))
    assert(newListMap.head == ("Canada" -> "Ottawa"))
  }

  "head() method" should "return the first inserted entry of the ListMap" in {
    assert(countryCapitals.head == ("Canada" -> "Ottawa"))
  }

  "tail() method" should "return a ListMap except the first entry from the initial ListMap" in {
    assert(countryCapitals.size == 3)
    assert(
      countryCapitals.tail == ListMap(
        "India" -> "Delhi",
        "Australia" -> "Canberra"
      )
    )
  }
}
