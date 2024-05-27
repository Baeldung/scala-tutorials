package com.baeldung.scala.spark.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.scalatest.flatspec.AnyFlatSpec

class RDDTutorialUnitTest extends AnyFlatSpec {

  val spark: SparkSession = SparkSession.builder.master("local").getOrCreate
  val sc = spark.sparkContext

  "animals collection" should "became RDD size 4" in {
    val animals = List("dog", "cat", "frog", "horse")
    val animalsRDD: RDD[String] = sc.parallelize(animals)
    assert(animalsRDD.count() == 4)
    assert(
      animalsRDD.collect() sameElements Array("dog", "cat", "frog", "horse")
    )
  }

  "animals rdd" should "filter animals without c" in {
    val animals = List("dog", "cat", "frog", "horse")
    val animalsRDD: RDD[String] = sc.parallelize(animals)
    val noCRDD = animalsRDD.filter(_.startsWith("c"))
    assert(noCRDD.collect() sameElements Array("cat"))
  }

  "numbers sum" should "be equals to" in {
    val numbers = sc.parallelize(List(1, 2, 3, 4, 5))
    val numbersSum = numbers.reduce(_ + _)
    assert(numbersSum == List(1, 2, 3, 4, 5).sum)
  }

  "joined rdd" should "contains both elements" in {
    val rddOne = sc.parallelize(List((1, "cat"), (2, "dog"), (3, "frog")))
    val rddTwo = sc.parallelize(List((1, "mammal"), (3, "amphibian")))
    val joinedRDD = rddOne.join(rddTwo).collect

    val firstElementJoined = ("cat", "mammal")
    val secondElementJoined = ("frog", "amphibian")

    assert(joinedRDD.head._2 == firstElementJoined)
    assert(joinedRDD.last._2 == secondElementJoined)
  }
}
