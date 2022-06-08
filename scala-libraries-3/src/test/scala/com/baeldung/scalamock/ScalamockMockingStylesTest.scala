package com.baeldung.scalamock

import org.scalamock.scalatest.MockFactory
import org.scalatest.wordspec.AnyWordSpec

class ScalamockMockingStylesTest extends AnyWordSpec with MockFactory {

  "MockingStyle" should {
    trait MockitoWannabe {
      def foo(i: Int): Int
    }
    "record and then verify" in {
      val mockedWannabe = stub[MockitoWannabe]
      (mockedWannabe.foo _).when(*).onCall((i: Int) => i * 2)
      assert(mockedWannabe.foo(12) === 24)
      (mockedWannabe.foo _).verify(12)
    }
  }

}
