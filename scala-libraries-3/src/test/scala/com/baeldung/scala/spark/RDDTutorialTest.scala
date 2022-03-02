package com.baeldung.scala.spark

import com.baeldung.scala.spark.RDDTutorial.{animalsRDD, noCRDD}
import org.scalatest.flatspec.AnyFlatSpec

class RDDTutorialTest extends AnyFlatSpec {

  "animals collection" should "became RDD size 4" in {
    assert(animalsRDD.count() == 4)
  }

  "animals rdd" should "filter animals without c" in {
    assert(noCRDD == ("cat"))
  }
}
