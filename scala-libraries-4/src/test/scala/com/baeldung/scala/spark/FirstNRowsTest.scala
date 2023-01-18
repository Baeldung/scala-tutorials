package com.baeldung.scala.spark
import sparkUtil.getSpark
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.apache.spark.sql.types.{StringType, IntegerType}
import org.apache.spark.sql.Row
import org.apache.spark.sql.Dataset
import info.{data, spark}
import spark.implicits._

class FirstNRowsSpec extends AnyFlatSpec with Matchers {

  "The data Dataframe" should "contain 6 elements" in {
    data.count() shouldBe (6)
  }

  it should "contain columns Name and Age" in {
    data.columns(0) shouldBe ("Name")
    data.columns(1) shouldBe ("Age")
  }

  it should "contain columns of type StringType and IntegerType" in {
    data.schema("Name").dataType shouldBe an[StringType]
    data.schema("Age").dataType shouldBe an[IntegerType]
  }

  "The show() method" should "return a Unit value" in {
    data.show() shouldBe an[Unit]
  }

  "The head(2) method" should "return first 2 rows of the data Dataframe" in {
    data.head(2) shouldBe (Array(
      Row("Ann", 25),
      Row("Brian", 16)
    ))
  }

  "The take(3) method" should "return the first 3 rows of the data Dataframe" in {
    data.take(3) shouldBe (Array(
      Row("Ann", 25),
      Row("Brian", 16),
      Row("Jack", 35)
    ))
  }

  "The limit(2) method" should "return a new DataSet with first 2 elements" in {
    data.limit(2) shouldBe an[Dataset[Row]]
    data.limit(2).count() shouldBe (2)
  }

  "The first() method" should "return the first row of the data Dataframe" in {
    data.first() shouldBe (Row("Ann", 25))
  }
}
