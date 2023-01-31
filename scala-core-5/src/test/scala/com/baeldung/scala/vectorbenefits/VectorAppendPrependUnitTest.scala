package com.baeldung.scala.vectorbenefits
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class VectorAppendPrependUnitTest extends AnyFlatSpec with Matchers{
  "appendPrependSeq" should "be able to append or prepend elements to a Sequence" in {
    val vec:Vector[Int] = Vector()
    val numElements = 10000
    val appendFunc = (a:Seq[Int],b:Int) => a:+b
    val expectedVec = (0 until numElements).toVector
    VectorAppendPrepend.appendPrependSeq(vec, appendFunc, numElements)._1  shouldEqual expectedVec
  }
}