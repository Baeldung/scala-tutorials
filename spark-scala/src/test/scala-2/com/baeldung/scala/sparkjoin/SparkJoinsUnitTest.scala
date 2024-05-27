package com.baeldung.scala.sparkjoin

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.apache.spark.sql.types.{StringType, IntegerType}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.Column
import com.baeldung.scala.sparkjoin.SparkJoins._

class SparkJoinsUnitTest extends AnyFlatSpec with Matchers {

  val spark =
    SparkSession.builder().appName("Joins").master("local").getOrCreate()
  import spark.implicits._

  val topHorrorMap: Map[Int, String] = Map(
    0 -> "AVC Movie Picks",
    1 -> "Director_AVC",
    2 -> "IGN Movie Picks",
    3 -> "Director_IGN"
  )

  "TopHorrorIGN2022" should "contain columns IMDB Rating and IGN Movie Picks" in {
    TopHorrorsIGN2022.columns(0) shouldBe ("IMDB Rating")
    TopHorrorsIGN2022.columns(1) shouldBe ("IGN Movie Picks")
  }

  "TopHorrorsTheAVClub2022" should "contain columns IMDB Rating and IGN Movie Picks" in {
    TopHorrorsTheAVClub2022.columns(0) shouldBe ("IMDB Rating")
    TopHorrorsTheAVClub2022.columns(1) shouldBe ("AVC Movie Picks")
  }

  "TopHorrors2022" should "contain columns AVC Movie Picks, Director_AVC, IGN Movie Picks and Director_IGN" in {
    topHorrorMap.map { case (k, v) =>
      TopHorrors2022.columns(k) shouldBe (v)
    }
  }

  "TopHorrors2022, TopHorrorsIGN2022, TopHorrorsTheAVClub2022, rightOuterJoin, selfJoin and selfJoin_v2" should "each contain 5 rows" in {
    List(
      TopHorrorsIGN2022,
      TopHorrorsTheAVClub2022,
      rightOuterJoin,
      leftOuterJoin,
      TopHorrors2022
    ).map { dataFrame =>
      dataFrame.count() shouldBe (5)
    }
  }

  it should "contain columns of type StringType" in {
    List(TopHorrorsIGN2022, TopHorrorsTheAVClub2022).map { dataFrame =>
      dataFrame.schema("IMDB Rating").dataType shouldBe an[IntegerType]
    }
    TopHorrorsIGN2022.schema("IGN Movie Picks").dataType shouldBe an[StringType]
    TopHorrorsTheAVClub2022
      .schema("AVC Movie Picks")
      .dataType shouldBe an[StringType]
    topHorrorMap.map { case (_, v) =>
      TopHorrors2022.schema(v).dataType shouldBe an[StringType]
    }

  }

  "query" should "be of type Column" in {
    query shouldBe an[Column]
  }

  "innerJoin, innerJoin_v2, leftAntiJoin" should "each contain 3 rows" in {
    List(innerJoin, innerJoin_v2, leftAntiJoin).map { dataCount =>
      dataCount.count() shouldBe (3)
    }
  }

  "outerJoin" should "contain 8 rows" in {
    outerJoin.count() shouldBe (8)
  }

  "leftSemiJoin" should "contain 2 rows" in {
    List(leftSemiJoin, selfJoin).map { dataFrame =>
      dataFrame.count() shouldBe (2)
    }
  }

  "crossJoin" should "contain 25 rows" in {
    crossJoin.count() shouldBe (25)
  }
}
