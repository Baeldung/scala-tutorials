package com.baeldung.scala.conversions

import org.scalatest.FlatSpec
import org.scalatest.matchers.should.Matchers

import java.util.ArrayList

class ConversionWrappersUnitTest extends FlatSpec with Matchers {

  import scala.collection.JavaConverters._

  "Round trip conversions from Java to Scala" should "not have any overhead" in {
    val javaList = new ArrayList[Int]
    javaList.add(1)
    javaList.add(2)
    javaList.add(3)
    assert(javaList eq javaList.asScala.asJava)
  }

  "Round trip conversions from Scala to Java" should "not have any overhead" in {
    val scalaSeq = Seq(1, 2, 3).toIterator
    assert(scalaSeq eq scalaSeq.asJava.asScala)
  }

  "Conversions to mutable collections" should "throw an unsupported operation exception" in {
    val scalaSeq = Seq(1, 2, 3)
    val javaList = scalaSeq.asJava
    assertThrows[UnsupportedOperationException](javaList.add(4))
  }
}
