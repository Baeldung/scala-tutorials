package com.baeldung.scala.sparkJoin

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.Column
import com.baeldung.scala.sparkJoin.SparkJoins._

class SparkJoinsSpec extends AnyFlatSpec with Matchers {

  val spark =
    SparkSession.builder().appName("Joins").master("local").getOrCreate()
  import spark.implicits._

  "TopHorrorIGN2022" should "contain columns IMDB Rating and IGN Movie Picks" in {
    TopHorrorsIGN2022.columns(0) shouldBe ("IMDB Rating")
    TopHorrorsIGN2022.columns(1) shouldBe ("IGN Movie Picks")
  }

  "TopHorrorsTheAVClub2022" should "contain columns IMDB Rating and IGN Movie Picks" in {
    TopHorrorsTheAVClub2022.columns(0) shouldBe ("IMDB Rating")
    TopHorrorsTheAVClub2022.columns(1) shouldBe ("AVC Movie Picks")
  }

  "TopHorrorsIGN2022, TopHorrorsTheAVClub2022, rightOuterJoin, selfJoin and selfJoin_v2" should "each contain 5 rows" in {
    List(
      TopHorrorsIGN2022,
      TopHorrorsTheAVClub2022,
      rightOuterJoin,
      selfJoin,
      selfJoin_v2
    ).map { dataFrame =>
      dataFrame.count() shouldBe (5)
    }
  }

  it should "contain columns of type StringType" in {
    List(TopHorrorsIGN2022, TopHorrorsTheAVClub2022).map { dataFrame =>
      dataFrame.schema("IMDB Rating").dataType shouldBe an[StringType]
    }
    TopHorrorsIGN2022.schema("IGN Movie Picks").dataType shouldBe an[StringType]
    TopHorrorsTheAVClub2022
      .schema("AVC Movie Picks")
      .dataType shouldBe an[StringType]
  }

  "movieQuery" should "be of type Column" in {
    movieQuery shouldBe an[Column]
  }

  "innerJoinWithQuery, innerJoin, leftAntiJoin" should "each contain 3 rows" in {
    List(innerJoinWithQuery, innerJoin, leftAntiJoin).map { dataCount =>
      dataCount.count() shouldBe (3)
    }
  }

  "outerJoin" should "contain 8 rows" in {
    outerJoin.count() shouldBe (8)
  }

  "leftOuterJoin" should "contain 6 rows" in {
    leftOuterJoin.count() shouldBe (6)
  }

  "leftSemiJoin" should "contain 2 rows" in {
    leftSemiJoin.count() shouldBe (2)
  }

  "crossJoin" should "contain 25 rows" in {
    crossJoin.count() shouldBe (25)
  }
}
