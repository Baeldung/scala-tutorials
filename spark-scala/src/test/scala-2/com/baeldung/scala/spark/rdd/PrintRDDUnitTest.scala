package com.baeldung.scala.spark.rdd

import org.apache.spark.sql.SparkSession
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

class PrintRDDUnitTest extends AnyFlatSpec with Matchers {
  val spark: SparkSession = SparkSession.builder.master("local").getOrCreate

  "default rdd" should "have size six" in {
    val numbers = List(4, 6, 1, 7, 12, 2)
    val rdd = spark.sparkContext.parallelize(numbers)
    assert(rdd.count() == 6)
  }

  "default rdd" should "convert to array" in {
    val numbers = List(4, 6, 1, 7, 12, 2)
    val rdd = spark.sparkContext.parallelize(numbers)
    val collectConvertion = rdd.collect()
    assert(collectConvertion.size == 6)
    collectConvertion mustBe a[Array[Int]]
  }

  "default rdd" should "convert to a limited array" in {
    val numbers = List(4, 6, 1, 7, 12, 2)
    val rdd = spark.sparkContext.parallelize(numbers)
    val collectConvertion = rdd.take(3)
    assert(collectConvertion.size == 3)
    collectConvertion mustBe a[Array[Int]]
  }
}
