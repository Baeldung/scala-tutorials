package com.baeldung.scala.spark.rdd

import org.apache.spark.sql.SparkSession

object PrintRDD extends App {
  val spark: SparkSession = SparkSession.builder().master("local").getOrCreate
  import spark.implicits._

  val numbers = List(4, 6, 1, 7, 12, 2)
  val rdd = spark.sparkContext.parallelize(numbers)

  rdd.foreach(println)

  /** 4 6 1 7 12 2
    */

  val collectConvertion = rdd.collect()
  collectConvertion.foreach(println)

  /** 4 6 1 7 12 2
    */

  val takeConvertion = rdd.take(3)
  takeConvertion.foreach(println)

  /** 4 6 1
    */

  val df = spark.sparkContext
    .parallelize(
      Seq(
        ("Math", "Intermediate", 800),
        ("English", "Basic", 500),
        ("Science", "Advanced", 400)
      )
    )
    .toDF("Subject", "Level", "Duration")

  df.show()

  /** | Subject |        Level | Duration |
    * |:--------|-------------:|:---------|
    * | Math    | Intermediate | 800      |
    * | English |        Basic | 500      |
    * | Science |     Advanced | 400      |
    */
}
