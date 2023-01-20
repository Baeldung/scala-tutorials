package com.baeldung.scala.implicitimports

import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.exceptions.{TestFailedException, TestCanceledException}
import scala.util.{Failure, Success}

class ImplicitImportsUnitTest extends FlatSpec with Matchers {

  "???" should "return exception" in {
    def notImplemeted: Int => Boolean = ???
    assertThrows[NotImplementedError] {
      notImplemeted
    }
  }

  "identity" should "return the input vlue" in {
    val input = "some"
    identity("some") should be("some")
  }

  "implicity" should "return implicit value of given type" in {
    implicit val a = "test"
    implicitly[String] should be("test")
  }

  "assert" should "throw AssertionError when false" in {
    assertThrows[TestFailedException] {
      assert(2 + 2 == 5)
    }
  }

  "assume" should "throw AssertionError   when false" in {
    assertThrows[TestCanceledException] {
      assume(2 + 2 == 5)
    }
  }

  "require" should "throw AssertionError when false" in {
    assertThrows[IllegalArgumentException] {
      require(2 + 2 == 5)
    }
  }

  "Java String" should "be implicityly convereted to StringOps" in {
    "hello".filter(_ != 'h') should be("ello")
  }
}
