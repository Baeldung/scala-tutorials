package com.baeldung.scala.sparkJoins

import org.apache.spark.sql.SparkSession

object SparkJoins {
  // Creating a spark session
  val spark =
    SparkSession.builder().appName("Joins").master("local").getOrCreate()

  import spark.implicits._
  // Creating two databases to join
  val TopHorrorsIGN2022 = Seq(
    ("7.0", "Pearl"),
    ("6.5", "The Sadness"),
    ("4.9", "Offseason"),
    ("6.2", "Hatching"),
    ("6.6", "x")
  ).toDF("IMDB Rating", "IGN Movie Picks")

  val TopHorrorsTheAVClub2022 = Seq(
    ("6.8", "Nope"),
    ("7.0", "Pearl"),
    ("6.6", "x"),
    ("7.0", "Barbarian"),
    ("6.9", "Bones And All")
  ).toDF("IMDB Rating", "AVC Movie Picks")

  // Inner Joins
  val movieQuery =
    TopHorrorsIGN2022("IMDB Rating") === TopHorrorsTheAVClub2022("IMDB Rating")

  val innerJoinWithQuery =
    TopHorrorsIGN2022.join(TopHorrorsTheAVClub2022, movieQuery)

  // produces same results as innerJoinWithQuery
  val innerJoinWithQuery_v2 =
    TopHorrorsIGN2022.join(TopHorrorsTheAVClub2022, movieQuery, "inner")

  // taking care of duplicate columns
  val innerJoin = TopHorrorsIGN2022
    .join(TopHorrorsTheAVClub2022, Seq("IMDB Rating"), "inner")

  // Outer Join
  val outerJoin = TopHorrorsIGN2022
    .join(TopHorrorsTheAVClub2022, Seq("IMDB Rating"), "outer")

  // Left Outer Join
  val leftOuterJoin = TopHorrorsIGN2022
    .join(TopHorrorsTheAVClub2022, Seq("IMDB Rating"), "left_outer")

  // Right Outer Join
  val rightOuterJoin = TopHorrorsIGN2022
    .join(TopHorrorsTheAVClub2022, Seq("IMDB Rating"), "right_outer")

  // Left Semi Join
  val leftSemiJoin = TopHorrorsIGN2022
    .join(TopHorrorsTheAVClub2022, Seq("IMDB Rating"), "left_semi")

  // Left Anti Join
  val leftAntiJoin = TopHorrorsIGN2022
    .join(TopHorrorsTheAVClub2022, Seq("IMDB Rating"), "left_anti")

  // Cartesian/Cross Join
  val crossJoin = TopHorrorsIGN2022.crossJoin(TopHorrorsTheAVClub2022)

  // Self Join
  val selfJoin = TopHorrorsIGN2022
    .alias("df1")
    .join(TopHorrorsIGN2022.alias("df2"), Seq("IMDB Rating"), "inner")

  val myQuery =
    TopHorrorsIGN2022("IMDB Rating") === TopHorrorsIGN2022("IMDB Rating")
  val selfJoin_v2 = TopHorrorsIGN2022
    .alias("df1")
    .join(TopHorrorsIGN2022.alias("df2"), myQuery, "inner")
}
object SparkJoinsProgram extends App {
  import SparkJoins._

  innerJoinWithQuery.show()

  /** | IMDB Rating | IGN Movie Picks | IMDB Rating | AVC Movie Picks |
    * |:------------|:----------------|:------------|:----------------|
    * | 7.0         | Pearl           | 7.0         | Barbarian       |
    * | 7.0         | Pearl           | 7.0         | Pearl           |
    * | 6.6         | x               | 6.6         | x               |
    */

  innerJoin.show()

  /** | IMDB Rating | IGN Movie Picks | AVC Movie Picks |
    * |:------------|:----------------|:----------------|
    * | 7.0         | Pearl           | Barbarian       |
    * | 7.0         | Pearl           | Pearl           |
    * | 6.6         | x               | x               |
    */

  // Outer Join
  outerJoin.show()

  /** | IMDB Rating | IGN Movie Picks | AVC Movie Picks |
    * |:------------|:----------------|:----------------|
    * | 4.9         | Offseason       | null            |
    * | 6.2         | Hatching        | null            |
    * | 6.5         | The Sadness     | null            |
    * | 6.6         | x               | x               |
    * | 6.8         | null            | Nope            |
    * | 6.9         | null            | Bones And All   |
    * | 7.0         | Pearl           | Pearl           |
    * | 7.0         | Pearl           | Barbarian       |
    */

  // Left Outer Join
  leftOuterJoin.show()

  /** | IMDB Rating | IGN Movie Picks | AVC Movie Picks |
    * |:------------|:----------------|:----------------|
    * | 7.0         | Pearl           | Barbarian       |
    * | 7.0         | Pearl           | Pearl           |
    * | 6.5         | The Sadness     | null            |
    * | 4.9         | Offseason       | null            |
    * | 6.2         | Hatching        | null            |
    * | 6.6         | x               | x               |
    */

  // Right Outer Join
  rightOuterJoin.show()

  /** | IMDB Rating | IGN Movie Picks | AVC Movie Picks |
    * |:------------|:----------------|:----------------|
    * | 6.8         | null            | Nope            |
    * | 7.0         | Pearl           | Pearl           |
    * | 6.6         | x               | x               |
    * | 7.0         | Pearl           | Barbarian       |
    * | 6.9         | null            | Bones And All   |
    */

  // Left Semi Join
  leftSemiJoin.show()

  /** | IMDB Rating | IGN Movie Picks |
    * |:------------|:----------------|
    * | 7.0         | Pearl           |
    * | 6.6         | x               |
    */

  // Left Anti Join
  leftAntiJoin.show()

  /** | IMDB Rating | IGN Movie Picks |
    * |:------------|:----------------|
    * | 6.5         | The Sadness     |
    * | 4.9         | Offseason       |
    * | 6.2         | Hatching        |
    */

  // Cartesian/Cross Join
  crossJoin.show()

  /** | IMDB Rating | IGN Movie Picks | IMDB Rating | AVC Movie Picks |
    * |:------------|:----------------|:------------|:----------------|
    * | 7.0         | Pearl           | 6.8         | Nope            |
    * | 7.0         | Pearl           | 7.0         | Pearl           |
    * | 7.0         | Pearl           | 6.6         | x               |
    * | 7.0         | Pearl           | 7.0         | Barbarian       |
    * | 7.0         | Pearl           | 6.9         | Bones And All   |
    * | 6.5         | The Sadness     | 6.8         | Nope            |
    * | 6.5         | The Sadness     | 7.0         | Pearl           |
    * | 6.5         | The Sadness     | 6.6         | x               |
    * | 6.5         | The Sadness     | 7.0         | Barbarian       |
    * | 6.5         | The Sadness     | 6.9         | Bones And All   |
    * | 4.9         | Offseason       | 6.8         | Nope            |
    * | 4.9         | Offseason       | 7.0         | Pearl           |
    * | 4.9         | Offseason       | 6.6         | x               |
    * | 4.9         | Offseason       | 7.0         | Barbarian       |
    * | 4.9         | Offseason       | 6.9         | Bones And All   |
    * | 6.2         | Hatching        | 6.8         | Nope            |
    * | 6.2         | Hatching        | 7.0         | Pearl           |
    * | 6.2         | Hatching        | 6.6         | x               |
    * | 6.2         | Hatching        | 7.0         | Barbarian       |
    * | 6.2         | Hatching        | 6.9         | Bones And All   |
    * only showing top 20 rows
    */

  // Self Join
  selfJoin.show()

  /** | IMDB Rating | IGN Movie Picks | IGN Movie Picks |
    * |:------------|:----------------|:----------------|
    * | 7.0         | Pearl           | Pearl           |
    * | 6.5         | The Sadness     | The Sadness     |
    * | 4.9         | Offseason       | Offseason       |
    * | 6.2         | Hatching        | Hatching        |
    * | 6.6         | x               | x               |
    */

  selfJoin_v2.show()

  /** | IMDB Rating | IGN Movie Picks | IMDB Rating | IGN Movie Picks |
    * |:------------|:----------------|:------------|:----------------|
    * | 7.0         | Pearl           | 7.0         | Pearl           |
    * | 6.5         | The Sadness     | 6.5         | The Sadness     |
    * | 4.9         | Offseason       | 4.9         | Offseason       |
    * | 6.2         | Hatching        | 6.2         | Hatching        |
    * | 6.6         | x               | 6.6         | x               |
    */

  spark.close()
}
