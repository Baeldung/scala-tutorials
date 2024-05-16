package com.baeldung.scala.filtermap

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import FilterMap._

class FilterMapUnitTest
  extends AnyFlatSpec
  with Matchers
  with TableDrivenPropertyChecks {
  "xmen" should "be a Map of Rank and Option[String]" in {
    xmen shouldBe an[Map[Rank, Option[String]]]
  }
  "filterMap, filterNotMap, collectMap, forMap, filterKeysMap, filterKeysSet and withFilterMap on xmen" should "not include None values" in {
    val filteredMapTable = Table(
      "filteredXmenMap",
      filterMap,
      filterNotMap,
      collectMap,
      forMap,
      filterKeysMap.toMap,
      filterKeysSet.toMap,
      withFilterMap.map(x => x)
    )
    forAll(filteredMapTable) { (map: Map[Rank, Option[String]]) =>
      map.valuesIterator.exists(_.contains(None)) shouldBe false
    }
  }
  "filterInPlace on xmenMutable" should "not include None values" in {
    xmenMutable.valuesIterator.exists(_.contains(None)) shouldBe false
  }
}
