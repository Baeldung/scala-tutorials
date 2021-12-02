package com.baedung.scala.collections

import org.scalatest.FlatSpec
import com.baeldung.scala.collections.IterateMap

class IterateMapTest extends FlatSpec {
  "Keys" should "return four chars" in {
    assert(IterateMap.values.size == 4)
  }

  "Values" should "return four integer" in {
    assert(IterateMap.values.size == 4)
  }
}
