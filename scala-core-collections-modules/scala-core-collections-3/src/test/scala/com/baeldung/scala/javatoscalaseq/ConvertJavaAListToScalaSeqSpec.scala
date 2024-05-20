package com.baeldung.scala.javatoscalaseq

import java.util
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ConvertJavaAListToScalaSeqSpec extends AnyFlatSpec with Matchers {
  val list: util.List[String] = util.ArrayList[String]()
  list.add("hello")
  list.add(" world")

  "212" should "return String" in {
    ConvertJavaAListToScalaSeq.in212(list) shouldBe "hello world"
  }

  "213" should "return String" in {
    ConvertJavaAListToScalaSeq.in213(list) shouldBe "hello world"
  }
}
