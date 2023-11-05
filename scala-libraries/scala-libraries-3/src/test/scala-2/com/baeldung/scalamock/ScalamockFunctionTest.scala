package com.baeldung.scalamock

import org.scalamock.scalatest.MockFactory
import org.scalatest.wordspec.AnyWordSpec

class ScalamockFunctionTest extends AnyWordSpec with MockFactory {

  "MockedFunction" should {

    "mock simple functions" in {
      val mockF = mockFunction[Int, Int]
      mockF.expects(*).onCall((i: Int) => i * 2).anyNumberOfTimes()

      assert(mockF.apply(1) === 2)
      assert(mockF.apply(11) === 22)
    }

    "for mockito comparison" in {
      trait Foo {
        def call(f: Int => String, i: Int): String
      }
      val mockedFoo = mock[Foo]
      (mockedFoo.call _)
        .expects(*, *)
        .onCall((f: Int => String, i: Int) => Range(0, i).mkString(","))
      assert(mockedFoo.call(_ => "bla", 3) === "0,1,2")
    }

    "mock overloaded variants" in {
      trait Overloader {
        def f(i: Int): String
        def f(s: String): String
        def f(t: (Int, String)): String
      }
      val mockedOverloader = mock[Overloader]
      (mockedOverloader
        .f(_: Int))
        .expects(*)
        .onCall((i: Int) => s"Int variant $i")
      (mockedOverloader
        .f(_: String))
        .expects(*)
        .onCall((i: String) => s"String variant $i")
      (mockedOverloader
        .f(_: (Int, String)))
        .expects(*)
        .onCall((i: (Int, String)) => s"Tuple variant (${i._1}, ${i._2})")

      assert(mockedOverloader.f(1) === "Int variant 1")
      assert(mockedOverloader.f("str") === "String variant str")
      assert(mockedOverloader.f((1, "str")) === "Tuple variant (1, str)")
    }

    "mock curried functions" in {
      trait CurryFunc {
        def curried(i: Int)(str: String): List[String]
      }
      val mockedCurryFunc = mock[CurryFunc]
      (mockedCurryFunc
        .curried(_: Int)(_: String))
        .expects(*, *)
        .onCall((i, str) => Range(0, i).map(num => s"$str-$num").toList)
      assert(mockedCurryFunc.curried(2)("myStr") === List("myStr-0", "myStr-1"))
    }

    "use implicits" in {
      trait WithImplicit {
        def func(i: Int)(implicit j: BigDecimal): BigDecimal
      }
      val mockedWithImplicit = mock[WithImplicit]
      (mockedWithImplicit
        .func(_: Int)(_: BigDecimal))
        .expects(*, *)
        .returning(BigDecimal(41))
      implicit val bigD = BigDecimal(12)
      assert(mockedWithImplicit.func(0) === BigDecimal(41))
    }
  }
}
