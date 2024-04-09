package com.baeldung.scala.zio.prelude.mapreduce

import org.scalatest.wordspec.AnyWordSpec

class MapReduceTest extends AnyWordSpec {

  import MapReduce._

  val book: List[String] =
    "This eBook is for the use of anyone anywhere at no cost and with" ::
      "almost no restrictions whatsoever.  You may copy it, give it away or" ::
      "re-use it under the terms of the Project Gutenberg License included" ::
      "with this eBook or online at www.gutenberg.org" :: Nil

  "MapReduce" should {
    "count correctly words without abstractions" in {
      val n = wordCount(book)
      assertResult(45)(n)
    }
    "count correctly words abstracting the map function" in {
      val n = wordCountAbstract(book)
      assertResult(45)(n)
    }
    "count correctly words abstracting also the data structure" in {
      val n = wordCountVeryAbstract(book)
      assertResult(45)(n)
    }
  }
}
