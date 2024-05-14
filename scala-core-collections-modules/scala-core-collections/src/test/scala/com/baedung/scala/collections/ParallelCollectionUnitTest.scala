package com.baedung.scala.collections

import com.baeldung.scala.collections.ParallelCollections._
import org.scalatest.flatspec.AnyFlatSpec

class ParallelCollectionUnitTest extends AnyFlatSpec {
  val ZERO_TO_HUNDRED_SUM = 5050
  val ZERO_TO_HUNDRED_SUM_TIMES2 = 10100

  "Parallel list of 100 filtered" should "return half size" in {
    assert(parallelFilter.size == 50)
  }

  "Parallel list of 100" should "sum" in {
    assert(parallelFold == ZERO_TO_HUNDRED_SUM)
  }

  "Parallel list of 100 multiplied by 2" should "sum" in {
    assert(parallelMap.sum == ZERO_TO_HUNDRED_SUM_TIMES2)
  }

  "Parallel list of 100 map" should "return same size" in {
    assert(parallelMap.size == parallelList.size)
  }

  "Parallel vector" should "size" in {
    assert(parallelVector.size == 1000)
  }

  "Other parallel vector" should "size" in {
    assert(otherParallelVector.size == 1000)
  }
}
