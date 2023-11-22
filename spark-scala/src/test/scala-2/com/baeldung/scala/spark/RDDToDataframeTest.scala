package com.baeldung.scala.spark

import com.baeldung.scala.spark.RDDToDataframe.convertRowRDDToDataframe
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{
  IntegerType,
  StringType,
  StructField,
  StructType
}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
class RDDToDataframeTest extends AnyFlatSpec with should.Matchers {

  val spark: SparkSession = SparkSession.builder.master("local").getOrCreate
  "convertRowRDDToDataframe" should "be able to convert an RDD[Row] and return a DataFrame" in {
    val rdd = spark.sparkContext.parallelize(
      Seq(
        Row("John", "Manager", 38),
        Row("Mary", "Director", 45),
        Row("Sally", "Engineer", 30)
      )
    )
    val schema = new StructType()
      .add(StructField("Name", StringType, false))
      .add(StructField("Job", StringType, true))
      .add(StructField("Age", IntegerType, true))

    val df = convertRowRDDToDataframe(rdd, schema, spark)

    df.schema.fields shouldEqual Array(
      StructField("Name", StringType, false),
      StructField("Job", StringType, true),
      StructField("Age", IntegerType, true)
    )
  }
}
