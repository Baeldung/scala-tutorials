package com.baedung.scala.collections

import org.scalatest.flatspec.AnyFlatSpec
import com.baeldung.scala.collections.IterateMap

class IterateMapUnitTest extends AnyFlatSpec {
  "Keys" should "return four chars" in {
    assert(IterateMap.keys.size == 4)
    assert(IterateMap.keys.mkString("") == "abcd")
  }

  "Values" should "return four integer" in {
    assert(IterateMap.values.size == 4)
    assert(IterateMap.values.mkString("") == "979899100")
  }
}
