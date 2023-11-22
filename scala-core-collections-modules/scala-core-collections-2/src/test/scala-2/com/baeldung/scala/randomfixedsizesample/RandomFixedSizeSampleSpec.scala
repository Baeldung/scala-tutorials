package com.baeldung.scala.randomfixedsizesample

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

import scala.annotation.tailrec;

class RandomFixedSizeSampleSpec extends AnyWordSpec with Matchers {

  "RandomFixedSizeSample" should {
    "create a random sample out of the initial List" in {
      val list = List.range(0, 100)
      val sampleSize = 10
      val list_0 = RandomFixedSizeSample.getRandomSampleRec(list, sampleSize)
      val list_1 = RandomFixedSizeSample.getRandomSampleZip(list, sampleSize)
      val list_2 =
        RandomFixedSizeSample.getRandomSampleShuffle(list, sampleSize)

      list_0.size shouldBe sampleSize
      list_1.size shouldBe sampleSize
      list_2.size shouldBe sampleSize

      list_0.toSet.size shouldBe list_0.size
      list_1.toSet.size shouldBe list_1.size
      list_2.toSet.size shouldBe list_2.size

      isSorted(list_0) shouldBe false
      isSorted(list_1) shouldBe false
      isSorted(list_2) shouldBe false
    }
    // We can't really depend on this test to check performance. So commenting this out
    /*"ensure getRandomSampleShuffle is the most performant, then goes getRandomSampleZip and then getRandomSampleRec" in {
      val list = List.range(0, 10_000)
      val sampleSize = 100

      val start_0 = System.nanoTime()
      RandomFixedSizeSample.getRandomSampleRec(list, sampleSize)
      val end_0 = System.nanoTime()
      val duration_0 = end_0 - start_0

      val start_1 = System.nanoTime()
      RandomFixedSizeSample.getRandomSampleZip(list, sampleSize)
      val end_1 = System.nanoTime()
      val duration_1 = end_1 - start_1

      val start_2 = System.nanoTime()
      RandomFixedSizeSample.getRandomSampleShuffle(list, sampleSize)
      val end_2 = System.nanoTime()
      val duration_2 = end_2 - start_2

      duration_0 should be > duration_1
      duration_1 should be > duration_2
    }*/

  }

  def isSorted[T](list: List[T])(implicit o: Ordering[T]): Boolean = {
    @tailrec def iter(head: T, tail: List[T]): Boolean =
      if (tail.isEmpty) true
      else if (o.lt(tail.head, head)) false
      else {
        iter(tail.head, tail.tail)
      }

    list match {
      case Nil          => true
      case head :: tail => iter(head, tail)
    }
  }

}
