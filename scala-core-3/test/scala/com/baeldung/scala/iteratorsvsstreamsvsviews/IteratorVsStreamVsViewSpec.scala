package scala.com.baeldung.scala.iteratorsvsstreamsvsviews

import com.baeldung.scala.iteratorsvsstreamsvsviews.NonStrictDataStructures
import org.scalatest.{Matchers, WordSpec}

class IteratorVsStreamVsViewSpec extends WordSpec with Matchers {

  "an iterator of collection" should {
    "be exhausted after applying foreach on it" in {
      val it = NonStrictDataStructures.iter
      it.foreach(_ + 1)
      it.hasNext shouldBe false
    }
  }

  "an iterator of collection" should {
    "have next element after applying map on it" in {
      val it = NonStrictDataStructures.data.iterator // in case of running all tests, the iterator should be got again
      val itUpd = it.map(_ + 1)
      itUpd.next shouldBe 1
    }
  }

  "Stream" should {
    "provide access to elements just like a List" in {
      val stream = NonStrictDataStructures.stream
      stream.head shouldBe 0
      stream(1) shouldBe 1
    }
  }

  "View's .force" should {
    "get collection back to original" in {
      val view = NonStrictDataStructures.view
      view.force shouldBe NonStrictDataStructures.data
    }
  }

}
