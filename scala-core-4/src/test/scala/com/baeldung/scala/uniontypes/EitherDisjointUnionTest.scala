package com.baeldung.scala.uniontypes
import org.scalatest.{Matchers, FlatSpec}
import com.baeldung.scala.uniontypes.EitherDisjointUnion._

class EitherDisjointUnionTest extends FlatSpec with Matchers {
  "isIntOrString" should "be able to take an integer parameter" in {
    isIntOrString(Left(10)) shouldEqual "10 is an Integer"
  }

  "isIntOrString" should "be able to take an string parameter" in {
    isIntOrString(Right("hello")) shouldEqual "hello is a String"
  }
}
