package com.baeldung.scala.vectorbenefits

import org.scalatest.{FlatSpec, Matchers}

class VectorHeadTailAccessTest extends FlatSpec with Matchers{
  "headTailAccessSeq" should "be able to access head/tail element of a Sequence" in {
    val numElements = 10000
    val vec:Vector[Int] = (1 to numElements).toVector

    val headAccessFunc = (a:Seq[Int]) => a.head
    val headElem = 1
    VectorHeadTailAccess.headTailAccessSeq(vec, headAccessFunc)._1  shouldEqual headElem

    val tailAccessFunc = (a:Seq[Int]) => a.last
    val tailElem = numElements
    VectorHeadTailAccess.headTailAccessSeq(vec, tailAccessFunc)._1  shouldEqual tailElem
  }
}