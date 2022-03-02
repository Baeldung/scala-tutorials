package com.baeldung.scala.spark

import com.baeldung.scala.spark.RDDTutorial.{animalsRDD, joinedRDD, noCRDD, numbersSum}
import org.scalatest.flatspec.AnyFlatSpec

class RDDTutorialTest extends AnyFlatSpec {

  "animals collection" should "became RDD size 4" in {
    assert(animalsRDD.count() == 4)
    assert(animalsRDD.collect() sameElements Array("dog", "cat", "frog", "horse"))
  }

  "animals rdd" should "filter animals without c" in {
    assert(noCRDD.collect() sameElements Array("cat"))
  }

  "numbers sum" should "be equals to" in {
    assert(numbersSum == List(1, 2, 3, 4, 5).sum)
  }

  "joined rdd" should "contains both elements" in {
    val firstElementJoined = ("cat", "mammal")
    val secondElementJoined = ("frog", "amphibian")

    assert(joinedRDD.head._2 == firstElementJoined)
    assert(joinedRDD.last._2 == secondElementJoined)
  }


}
