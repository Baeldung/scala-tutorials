package com.baeldung.scala.spark

import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.rdd.RDD

object RDDToDataframe extends App{

  val spark: SparkSession = SparkSession.builder.master("local").getOrCreate
  val sc = spark.sparkContext

  val rdd = sc.parallelize(
    Seq(
      ("John", "Manager", 38),
      ("Mary", "Director", 45),
      ("Sally", "Engineer", 30)
    )
  )

  val dfWitDefaultSchema = spark.createDataFrame(rdd)

  dfWitDefaultSchema.printSchema()
  /**
    |-- _1: string (nullable = true)
    |-- _2: string (nullable = true)
    |-- _3: integer (nullable = false)
   */

  dfWitDefaultSchema.show()
  /**
    +-----+--------+---+
    |   _1|      _2| _3|
    +-----+--------+---+
    | John| Manager| 38|
    | Mary|Director| 45|
    |Sally|Engineer| 30|
    +-----+--------+---+
   */

  def convertRowRDDToDataframe(rowRDD:RDD[Row], schema:StructType, spark: SparkSession) : DataFrame = {
    val dfWithSchema:DataFrame = spark.createDataFrame(rowRDD, schema)
    dfWithSchema
  }

  val rowRDD:RDD[Row] = rdd.map(t => Row(t._1, t._2, t._3))

  val schema = new StructType()
    .add(StructField("Name", StringType, false))
    .add(StructField("Job", StringType, true))
    .add(StructField("Age", IntegerType, true))

  val dfWithSchema:DataFrame = convertRowRDDToDataframe(rowRDD, schema, spark)

  dfWithSchema.show()
  /**
    +-----+--------+---+
    | Name|     Job|Age|
    +-----+--------+---+
    | John| Manager| 38|
    | Mary|Director| 45|
    |Sally|Engineer| 30|
    +-----+--------+---+
   */

  dfWithSchema.printSchema()
  /**
   |-- Name: string (nullable = false)
   |-- Job: string (nullable = true)
   |-- Age: integer (nullable = true)
   */

  import spark.implicits._

  val dfUsingToDFMethod = rdd.toDF("Name", "Job", "Age")

  dfUsingToDFMethod.printSchema()
  /**
    root
     |-- Name: string (nullable = true)
     |-- Job: string (nullable = true)
     |-- Age: integer (nullable = false)
   */

  dfUsingToDFMethod.show()
  /**
    +-----+--------+---+
    | Name|     Job|Age|
    +-----+--------+---+
    | John| Manager| 38|
    | Mary|Director| 45|
    |Sally|Engineer| 30|
    +-----+--------+---+
   */
}
