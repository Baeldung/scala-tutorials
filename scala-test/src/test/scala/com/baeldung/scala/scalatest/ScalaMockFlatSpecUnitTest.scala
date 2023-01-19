package com.baeldung.scala.scalatest

import org.scalatest.flatspec.AnyFlatSpec
import org.scalamock.scalatest.MockFactory
import org.scalatest.matchers.should.Matchers


class ScalaMockFlatSpec extends AnyFlatSpec with MockFactory with Matchers {

  "A mocked Foo" should "return a mocked bar value" in {
    val mockFoo = mock[Foo]
    (mockFoo.bar _).expects().returning(6)

    mockFoo.bar should be(6)
  }
}

class Foo {
  def bar = 100
}
