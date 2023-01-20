package com.baeldung.scala.vectorbenefits
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
class VectorIterationUnitTest extends AnyFlatSpec with Matchers{
  "calculateSumSeq" should "be able to calculate sum of all element of a Sequence" in {

    val vec:Vector[Int] = Vector(1,2,3,4,5)
    val expectedSum = 15
    VectorIteration.calculateSumSeq(vec)._1  shouldEqual expectedSum
  }
}