package com.baedung.scala.collections

import com.baeldung.scala.collections.ListSetExamples.{addElement, createEmptyListSet, createListSet, removeElement}
import org.scalatest.flatspec.AnyFlatSpec

import scala.collection.immutable.ListSet

class ListSetExamplesTest extends AnyFlatSpec {

  "The method createListSet " should "create a ListSet" in {
    val fruitsSet = createListSet("apple", "orange", "apple")
    assert(fruitsSet == ListSet("apple", "orange"))
  }

  "The method createEmptyListSet " should "create an empty ListSet" in {
    val emptySet = createEmptyListSet[String]
    assert(emptySet == ListSet())
  }

  "The method addElement " should "add an element to ListSet" in {
    val fruitsSet = createListSet("apple", "orange")
    val addedFruitsSet = addElement(fruitsSet, "grapes")
    assert(addedFruitsSet == ListSet("apple", "orange", "grapes"))
  }

  "The method removeElement " should "remove an element from ListSet" in {
    val fruitsSet = createListSet("apple", "orange", "grapes")
    val removedFruitsSet = removeElement(fruitsSet, "orange")
    assert(removedFruitsSet == ListSet("apple", "grapes"))
  }
}
