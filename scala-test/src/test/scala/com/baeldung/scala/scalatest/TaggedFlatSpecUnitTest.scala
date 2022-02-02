package com.baeldung.scala.scalatest

import org.scalatest.{FlatSpec, Matchers, Tag}

object BaeldungJavaTag extends Tag("com.baeldung.scala.scalatest.BaeldungJavaTag")

class TaggedFlatSpecUnitTest extends FlatSpec with Matchers {

  "Baeldung" should "be interesting" taggedAs (BaeldungJavaTag) in {
    "Baeldung has articles about Java" should include("Java")
  }

}
