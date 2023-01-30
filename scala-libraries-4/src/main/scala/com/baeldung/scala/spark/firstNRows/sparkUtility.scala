package com.baeldung.scala.spark
import org.apache.spark.sql.SparkSession

object sparkUtil {
  def getSpark(
    Name: String,
    Master: String = "local",
    partNo: String = "5",
    verbose: Boolean = true
  ): SparkSession = {
    val sparkSession =
      SparkSession.builder().appName(Name).master(Master).getOrCreate()
    sparkSession.conf.set("spark.sql.shuffle.partitions", partNo)
    if (verbose)
      println(s"Session started on spark version ${sparkSession.version}")
    return sparkSession
  }
}
