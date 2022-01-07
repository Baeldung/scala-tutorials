package com.baedung.scala.collections

import org.scalatest.FlatSpec

class ParallelCollectionTest extends FlatSpec {

  "Parallel list of 100" should "return half size" in {
    assert((1 to 100).toList.par.count(_ % 2 == 0) == 50)
  }

  "Parallel list of 100" should "sum" in {
    assert((1 to 100).toList.par.sum == 5050)
  }
}
