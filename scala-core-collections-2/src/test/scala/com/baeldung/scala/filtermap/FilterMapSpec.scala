package com.baeldung.scala.filtermap

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import FilterMap._

class FilterMapSpec extends AnyFlatSpec with Matchers with TableDrivenPropertyChecks {
  "xmen and xmen2" should "be a Map of Rank and Option[String]" in {
    Array(xmen, xmen2).foreach(_ shouldBe an[Map[Rank, Option[String]]])
  }
  "filterMap, filterNotMap, collectMap and forMap on xmen" should "not include None values" in {
    val filteredMapTable = Table(
      "filteredXmenMap",
      filterMap,
      filterNotMap,
      collectMap,
      forMap,
    )
    forAll(filteredMapTable) { map: Map[Rank, Option[String]] =>
      map.valuesIterator.exists(_.contains(None)) shouldBe false
    }
  }
}
