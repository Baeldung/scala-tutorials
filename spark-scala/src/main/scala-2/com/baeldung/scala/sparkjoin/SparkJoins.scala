package com.baeldung.scala.sparkjoin

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col

object SparkJoins {
  // Creating a spark session
  val spark =
    SparkSession.builder().appName("Joins").master("local").getOrCreate()

  import spark.implicits._
  // Creating two databases to join
  val TopHorrorsIGN2022 = Seq(
    (9, "Pearl"),
    (6, "The Sadness"),
    (6, "Offseason"),
    (7, "Hatching"),
    (8, "x")
  ).toDF("IMDB Rating", "IGN Movie Picks")

  val TopHorrorsTheAVClub2022 = Seq(
    (7, "Nope"),
    (9, "Pearl"),
    (8, "x"),
    (5, "Barbarian"),
    (5, "Bones And All")
  ).toDF("IMDB Rating", "AVC Movie Picks")

  val TopHorrors2022 = Seq(
    ("Nope", "Jordan Peele", "Pearl", "Ti West"),
    ("Pearl", "Ti West", "The Sadness", "Rob Jabbaz"),
    ("x", "Ti West", "Offseason", "Robert Cole"),
    ("Barbarian", "Zach Cregger", "Hatching", "Hanna Bergolm"),
    ("Bones And All", "Luca Guadagninino", "x", "Ti West")
  ).toDF("AVC Movie Picks", "Director_AVC", "IGN Movie Picks", "Director_IGN")

  // Inner Joins
  val innerJoin =
    TopHorrorsIGN2022.join(TopHorrorsTheAVClub2022, Seq("IMDB Rating"))

  // produces same results as innerJoinWithQuery
  val innerJoin_v2 =
    TopHorrorsIGN2022.join(TopHorrorsTheAVClub2022, Seq("IMDB Rating"), "inner")

  // Outer Join
  val cols = List(col("IGN Movie Picks"), col("AVC Movie Picks"))
  val query = TopHorrorsIGN2022(
    "IGN Movie Picks"
  ) === TopHorrorsTheAVClub2022("AVC Movie Picks")
  val outerJoin = TopHorrorsIGN2022
    .join(TopHorrorsTheAVClub2022, query, "outer")
    .select(cols: _*)

  // Left Outer Join
  val leftOuterJoin = TopHorrorsIGN2022
    .join(TopHorrorsTheAVClub2022, query, "left_outer")
    .select(cols: _*)

  // Right Outer Join
  val rightOuterJoin = TopHorrorsIGN2022
    .join(TopHorrorsTheAVClub2022, query, "right_outer")
    .select(cols: _*)

  // Left Semi Join
  val leftSemiJoin = TopHorrorsIGN2022
    .join(TopHorrorsTheAVClub2022, query, "left_semi")
    .select("IGN Movie Picks", "IMDB Rating")

  // Left Anti Join
  val leftAntiJoin = TopHorrorsIGN2022
    .join(TopHorrorsTheAVClub2022, query, "left_anti")
    .select("IGN Movie Picks", "IMDB Rating")

  // Cartesian/Cross Join
  val crossJoin = TopHorrorsIGN2022.crossJoin(TopHorrorsTheAVClub2022)

  // Self Join
  val selfJoin = TopHorrors2022
    .alias("df1")
    .join(
      TopHorrors2022.alias("df2"),
      col("df1.Director_AVC") === col("df2.Director_IGN"),
      "left_Semi"
    )
    .select("AVC Movie Picks", "Director_AVC")
}

object SparkJoinsProgram extends App {
  import SparkJoins._

  innerJoin.show()

  /** | IMDB Rating | IGN Movie Picks | AVC Movie Picks |
    * |:------------|:----------------|:----------------|
    * | 9           | Pearl           | Pearl           |
    * | 7           | Hatching        | Nope            |
    * | 8           | x               | x               |
    */

  innerJoin_v2.show()

  /** | IMDB Rating | IGN Movie Picks | AVC Movie Picks |
    * |:------------|:----------------|:----------------|
    * | 9           | Pearl           | Pearl           |
    * | 7           | Hatching        | Nope            |
    * | 8           | x               | x               |
    */

  // Outer Join
  outerJoin.show()

  /** | IGN Movie Picks | AVC Movie Picks |
    * |:----------------|:----------------|
    * | null            | Barbarian       |
    * | null            | Bones And All   |
    * | Hatching        | null            |
    * | null            | Nope            |
    * | Offseason       | null            |
    * | Pearl           | Pearl           |
    * | The Sadness     | null            |
    * | x               | x               |
    */

  // Left Outer Join
  leftOuterJoin.show()

  /** | IGN Movie Picks | AVC Movie Picks |
    * |:----------------|:----------------|
    * | Pearl           | Pearl           |
    * | The Sadness     | null            |
    * | Offseason       | null            |
    * | Hatching        | null            |
    * | x               | x               |
    */

  // Right Outer Join
  rightOuterJoin.show()

  /** | IGN Movie Picks | AVC Movie Picks |
    * |:----------------|:----------------|
    * | null            | Nope            |
    * | Pearl           | Pearl           |
    * | x               | x               |
    * | null            | Barbarian       |
    * | null            | Bones And All   |
    */

  // Left Semi Join
  leftSemiJoin.show()

  /** | IGN Movie Picks | IMDB Rating |
    * |:----------------|:------------|
    * | Pearl           | 9           |
    * | x               | 8           |
    */

  // Left Anti Join
  leftAntiJoin.show()

  /** | IGN Movie Picks | IMDB Rating |
    * |:----------------|:------------|
    * | The Sadness     | 6           |
    * | Offseason       | 6           |
    * | Hatching        | 7           |
    */

  // Cartesian/Cross Join
  crossJoin.show(5)

  /** | IMDB Rating | IGN Movie Picks | IMDB Rating | AVC Movie Picks |
    * |:------------|:----------------|:------------|:----------------|
    * | 9           | Pearl           | 7           | Nope            |
    * | 9           | Pearl           | 9           | Pearl           |
    * | 9           | Pearl           | 8           | x               |
    * | 9           | Pearl           | 5           | Barbarian       |
    * | 9           | Pearl           | 5           | Bones And All   |
    * only showing top 5 rows
    */

  // Self Join
  selfJoin.show()

  /** | AVC Movie Picks | Director_AVC |
    * |:----------------|:-------------|
    * | Pearl           | Ti West      |
    * | x               | Ti West      |
    */

  spark.close()
}
