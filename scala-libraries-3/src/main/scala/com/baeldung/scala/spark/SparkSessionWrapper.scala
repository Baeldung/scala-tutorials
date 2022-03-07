package com.baeldung.scala.spark

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

trait SparkSessionWrapper {
    val spark: SparkSession = SparkSession.builder.master("local").getOrCreate
    val sc: SparkContext = spark.sparkContext
}
