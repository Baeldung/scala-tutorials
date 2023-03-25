package com.baeldung.scala.spark.rdd

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object RDDTutorial extends App {
  val spark: SparkSession = SparkSession.builder().master("local").getOrCreate
  val sc: SparkContext = spark.sparkContext

  val animals = List("dog", "cat", "frog", "horse")
  val animalsRDD: RDD[String] = sc.parallelize(animals)

  val countLengthRDD = animalsRDD.map(animal => (animal, animal.length))
  val noCRDD = animalsRDD.filter(_.startsWith("c"))

  countLengthRDD.collect()
  noCRDD.collect()

  val numbers = sc.parallelize(List(1, 2, 3, 4, 5))
  val numbersSum = numbers.reduce(_ + _)

  val rddOne = sc.parallelize(List((1, "cat"), (2, "dog"), (3, "frog")))
  val rddTwo = sc.parallelize(List((1, "mammal"), (3, "amphibian")))
  val joinedRDD = rddOne.join(rddTwo).collect

  val data = List(1, 2, 3, 4, 5)
  val rdd = sc.parallelize(data)

  rdd.filter(_ % 2 == 0).map(_ * 2)

  rdd.toDebugString

  /** (8) MapPartitionsRDD[2] at map at <console>:26 [] \| MapPartitionsRDD[1]
    * at filter at <console>:26 [] \| ParallelCollectionRDD[0] at parallelize at
    * <console>:27 []
    */

  import spark.implicits._
  val df = rdd.toDF()
  df.filter($"value" % 2 === 0)
    .withColumn("value", $"value" * 2)
    .explain("formatted")

  /** ==Physical Plan==
    * Project (4) +- * Filter (3) +- * SerializeFromObject (2) +- Scan (1)
    *
    * (1) Scan Output [1]: [obj#1] Arguments: obj#1: int,
    * ParallelCollectionRDD[0] at parallelize at <console>:27
    *
    * (2) SerializeFromObject [codegen id : 1] Input [1]: [obj#1] Arguments:
    * [input[0, int, false] AS value#2]
    *
    * (3) Filter [codegen id : 1] Input [1]: [value#2] Condition : ((value#2 %
    * 2) = 0)
    *
    * (4) Project [codegen id : 1] Output [1]: [(value#2 * 2) AS value#11] Input
    * [1]: [value#2]
    */
}
