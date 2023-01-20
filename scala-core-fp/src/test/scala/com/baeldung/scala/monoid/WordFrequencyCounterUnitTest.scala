package com.baeldung.scala.monoid

import org.scalatest.wordspec.AnyWordSpec

class WordFrequencyCounterUnitTest extends AnyWordSpec {

  object WordFrequencyCounter {

    case class WordCount(word: String, count: Int)

    def wordCount(s: String): Map[String, Int] =
      s.split("\\s+")
        .map(x => WordCount(x, 1))
        .groupBy(w => w.word)
        .map(x => (x._1 -> x._2.foldLeft(0)((a, c) => c.count + a)))
        .toMap

    
  def frequency(wordCounts: Map[String, Int]*)(
      implicit monoid: Monoid[Map[String, Int]]
  ): Map[String, Int] =
    wordCounts.foldLeft(monoid.zero)(monoid.op(_, _))
  }
  def show(m: Map[String, Int]): Unit =
    m.keys.foreach(k => println(s"frequency $k: ${m(k)}"))

  val doc1 =
    """
      |monoid and monoid and monoid are good
     """.stripMargin
  val doc2 =
    """
      |monoid are good monoid are very good algebra
     """.stripMargin
  val doc3 =
    """
      |to monoid or not to monoid an algebra
     """.stripMargin

  "The word frequency counter" should {
    "combine the word count maps correctly" in {

      import WordFrequencyCounter._
      import MapMonoidInstance._
      val f1 = wordCount(doc1)
      val f2 = wordCount(doc2)
      val f3 = wordCount(doc3)
      implicit val monoid: Monoid[Map[String, Int]] = mapMonoid
      val combined = frequency(f1, f2, f3)

      /**
        * show(f1) // uncomment if you want to see the results
        * show(f2)
        * show(combined)
        */
      assert( combined("monoid") === 7 )
      assert( combined("algebra") === 2)
      assert(combined("are") === 3)
      assert(combined("an") === 1)
    }
  }
}
