package com.baeldung.scalamock

import com.baeldung.scalamock.model.{Model1, Model2}
import com.baeldung.scalamock.service.{UnitService1, UnitService2Impl}
import org.scalamock.matchers.ArgCapture.CaptureOne
import org.scalamock.scalatest.MockFactory
import org.scalatest.wordspec.AnyWordSpec

class ScalamockFeaturesTest extends AnyWordSpec with MockFactory {

  "ArgumentMatching" should {
    "call Service1 doSomething result" in {
      val m1 = Model1(1L, "Jim")
      val m2 = Model2(2L, "Timmy")
      val mockUnitService1 = mock[UnitService1]
      (mockUnitService1.doSomething _).expects(m1, m2)
      val unitService2 = new UnitService2Impl(mockUnitService1)
      unitService2.doSomething(m1, m2)
      succeed
    }

    "call Service1 doSomething result with wildcard" in {
      val m1 = Model1(1L, "Jim")
      val m2 = Model2(2L, "Timmy")
      val mockUnitService1 = mock[UnitService1]
      (mockUnitService1.doSomething _).expects(m1, *)
      val unitService2 = new UnitService2Impl(mockUnitService1)
      unitService2.doSomething(m1, m2)
      succeed
    }

    "call Service1 doSomething result with functional matcher" in {
      val m1 = Model1(1L, "Jim")
      val m2 = Model2(2L, "Timmy")
      val mockUnitService1 = mock[UnitService1]
      (mockUnitService1.doSomething _).expects(where {
        (_m1: Model1, _m2: Model2) => _m1.id == 1L && _m2.id == 2L
      })
      val unitService2 = new UnitService2Impl(mockUnitService1)
      unitService2.doSomething(m1, m2)
      succeed
    }

  }

  "EpsilonMatcher" should {
    case class Model3() {
      def numFunc(num: Float): Unit = {}
    }
    "match close numbers" in {
      val mockedModel3 = mock[Model3]
      (mockedModel3.numFunc _).expects(~71.5f)
      mockedModel3.numFunc(71.50002f) // success
      // mockedModel3.numFunc(71.502f) // failure
      succeed
    }
  }

  "Ordering" should {
    "verify that doSomething is called before doSomethingElse" in {
      val m1 = Model1(1L, "Jim")
      val m2 = Model2(2L, "Timmy")
      val mockUnitService1 = mock[UnitService1]
      inSequence {
        (mockUnitService1.doSomething _).expects(m1, m2)
        (mockUnitService1.doSomethingElse _).expects(m1, m2)
      }
      val unitService2 = new UnitService2Impl(mockUnitService1)
      unitService2.doManyThings(m1, m2) // success
      // unitService2.doManyThingsInverted(m1, m2) // failure
      succeed
    }

    "verify that doSomething and doSomethingElse are called in any order" in {
      val m1 = Model1(1L, "Jim")
      val m2 = Model2(2L, "Timmy")
      val mockUnitService1 = mock[UnitService1]
      inAnyOrder {
        (mockUnitService1.doSomething _).expects(m1, m2)
        (mockUnitService1.doSomethingElse _).expects(m1, m2)
      }
      val unitService2 = new UnitService2Impl(mockUnitService1)
      // unitService2.doManyThings(m1, m2) // also success
      unitService2.doManyThingsInverted(m1, m2) // success
      succeed
    }
  }

  "CallCounts" should {
    case class Model3() {
      def emptyFunc(): Unit = {}
    }
    "verify the number of calls" in {
      val mockedModel3 = mock[Model3]
      (mockedModel3.emptyFunc _).expects().once()
      // (mockedModel3.emptyFunc _).expects().twice() // exactly 2 times
      // (mockedModel3.emptyFunc _).expects().never() // never called
      // (mockedModel3.emptyFunc _).expects().repeat(4) // exactly 4 times
      // (mockedModel3.emptyFunc _).expects().repeat(5 to 12) // between 5 and 12 times
      mockedModel3.emptyFunc()
      succeed
    }
  }

  "Returning" should {
    case class Model3() {
      def getInt(): Int = 3
    }
    "verify the number of calls" in {
      val mockedModel3 = mock[Model3]
      (mockedModel3.getInt _).expects().returning(12)
      assert(mockedModel3.getInt() === 12)
    }
  }

  "ExceptionThrowing" should {
    case class Model3() {
      def getInt(): Int = 3
    }
    "throw exception on mock call" in {
      val mockedModel3 = mock[Model3]
      (mockedModel3.getInt _)
        .expects()
        .throwing(new RuntimeException("getInt called"))
      assertThrows[RuntimeException](mockedModel3.getInt())
    }
  }

  "CallHandlers" should {
    case class Model3() {
      def get(num: Int): Int = num - 1
    }
    "return argument plus 1" in {
      val mockedModel3 = mock[Model3]
      (mockedModel3.get _).expects(*).onCall((i: Int) => i + 1)
      assert(mockedModel3.get(4) === 5)
    }
  }

  "ArgumentCapture" should {
    trait OneArg {
      def func(arg: Int): Unit
    }
    "capture arguments" in {
      val mockedOneArg = mock[OneArg]
      val captor = CaptureOne[Int]()
      (mockedOneArg.func _).expects(capture(captor)).atLeastOnce()
      mockedOneArg.func(32)
      assert(captor.value === 32)
    }
  }

  "PolymorhicFunctions" should {
    "be mocked" in {
      trait Polymorphic {
        def call[A](arg: A): A
      }
      val mockPolymorphic = mock[Polymorphic]
      (mockPolymorphic.call[Int] _).expects(1).onCall((i: Int) => i * 2)
      assert(mockPolymorphic.call(1) === 2)
    }
  }

}
