package com.baeldung.scala.scalatest

import org.scalatest.{FlatSpec, Matchers}
import org.scalamock.scalatest.MockFactory


class ScalaMockFlatSpec extends FlatSpec with MockFactory with Matchers {

  "A mocked Foo" should "return a mocked bar value" in {
    val mockFoo = mock[Foo]
    mockFoo.bar _ expects() returning 6

    mockFoo.bar should be(6)
  }
}

class Foo {
  def bar = 100
}
