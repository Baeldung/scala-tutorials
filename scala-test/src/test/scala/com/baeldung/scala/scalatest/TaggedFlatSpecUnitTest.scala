package com.baeldung.scala.scalatest

import org.scalatest.{Tag}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

object BaeldungJavaTag extends Tag("com.baeldung.scala.scalatest.BaeldungJavaTag")

class TaggedFlatSpecUnitTest extends AnyFlatSpec with Matchers {

  "Baeldung" should "be interesting" taggedAs (BaeldungJavaTag) in {
    "Baeldung has articles about Java" should include("Java")
  }

}
