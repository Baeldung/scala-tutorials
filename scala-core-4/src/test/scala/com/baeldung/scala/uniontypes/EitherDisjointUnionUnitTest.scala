package com.baeldung.scala.uniontypes
import com.baeldung.scala.uniontypes.EitherDisjointUnion._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class EitherDisjointUnionUnitTest extends AnyFlatSpec with Matchers {
  "isIntOrString" should "be able to take an integer parameter" in {
    isIntOrString(Left(10)) shouldEqual "10 is an Integer"
  }

  "isIntOrString" should "be able to take an string parameter" in {
    isIntOrString(Right("hello")) shouldEqual "hello is a String"
  }
}
