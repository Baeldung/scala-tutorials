package com.baeldung.scala.spark

import com.baeldung.scala.spark.sparkUtil.getSpark

object info {
  val spark = getSpark("FirstNRows")
  import spark.implicits._

  val data = Seq(
    ("Ann", 25),
    ("Brian", 16),
    ("Jack", 35),
    ("Conrad", 27),
    ("Grace", 33),
    ("Richard", 40)
  ).toDF("Name", "Age")
}

object FirstNRows extends App {
  import info.{data, spark}

  data.show(3)

  /** | Name  | Age |
    * |:------|:----|
    * | Ann   | 25  |
    * | Brian | 16  |
    * | Jack  | 35  |
    * only showing top 3 rows
    */

  data.head(2).foreach(println)

  /** [Ann,25] [Brian,16]
    */

  data.take(2).foreach(println)

  /** [Ann,25] [Brian,16]
    */

  println(data.takeAsList(2))

  /** [[Ann,25], [Brian,16]]
    */

  data.limit(2).foreach(println(_))

  /** [Ann,25] [Brian,16]
    */

  data.limit(2).show()

  /** | Name  | Age |
    * |:------|:----|
    * | Ann   | 25  |
    * | Brian | 16  |
    */

  println(data.first())

  /** [Ann,25]
    */

  spark.close()
}
