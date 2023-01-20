package com.baeldung.scala.iteratorsvsstreamsvsviews

import com.baeldung.scala.iteratorsvsstreamsvsviews.NonStrictDataStructures
import org.scalatest.{Matchers, WordSpec}

class IteratorVsStreamVsViewUnitTest extends WordSpec with Matchers {

  "strict collections after transformations" should {
    "be evaluated immediately" in {
      val list = List(1, 2, 3)
      list.map(_ * 2) shouldBe List(2, 4, 6)
    }
  }

  "an iterator of collection" should {
    "be exhausted after applying foreach on it" in {
      val it = NonStrictDataStructures.iter
      it.foreach(_ + 1)
      it.hasNext shouldBe false
    }
  }

  "an iterator of collection" should {
    "have next element after applying map on it" in {
      val it =
        NonStrictDataStructures.data.iterator // in case of running all tests, the iterator should be got again
      val itUpd = it.map(_ + 1)
      itUpd.next shouldBe 1
    }
  }

  "Stream" should {
    "provide access to elements just like a List" in {
      val stream = NonStrictDataStructures.stream
      stream.head shouldBe 0
      stream(0) shouldBe 0
      stream(1) shouldBe 1
    }
  }

  "Factorial" should {
    "be implemented via Stream without the risk of stack overflow" in {
      val factorials7 = Factorial().factorialsN(7)
      val factorialsList = factorials7.toList
      factorialsList shouldBe List(1, 2, 6, 24, 120, 720, 5040)
    }
  }

  "Factorial" should {
    "throw StackOverflow error if implemented via List" in {
      def factorial(a: Int, b: Int): List[Int] =
        a :: factorial(a * (b + 1), b + 1)
      assertThrows[StackOverflowError] {
        factorial(1, 1).take(7)
      }
    }
  }

  "View's .force" should {
    "get collection back to original" in {
      val view = NonStrictDataStructures.view
      view.force shouldBe NonStrictDataStructures.data
    }
  }

  "View" should {
    "handle large set of data" in {
      val words: Iterable[String] =
        List.range(0, 100000).map(_ => "Java") ++ List("Scala") ++ List
          .range(0, 100000)
          .map(_ => "Java")
      val occurence = "Scala"
      val hasOccurence =
        (listOfWords: Iterable[String]) => listOfWords.exists(_ == occurence)

      hasOccurence(words.view) shouldBe true
    }
  }

  "View" should {
    "print results only when forced" in {
      def printer = println(System.currentTimeMillis())
      val printView = List.range(0, 10).view.map(_ => printer)
      val prRes = printView
      prRes.force
    }
  }

}
