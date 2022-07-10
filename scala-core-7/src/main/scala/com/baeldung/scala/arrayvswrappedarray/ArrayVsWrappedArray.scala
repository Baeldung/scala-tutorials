package com.baeldung.scala.`return`

import scala.collection.mutable

object ArrayVsWrappedArray extends App {
  private def simpleArrayTest(): Unit = {
    val simpleArray: Array[Int] = Array(1, 2, 3, 4, 5)
    print(simpleArray)
  }

  private def arrayToSequenceTest(): Unit = {
    val simpleArray: Array[Int] = Array(1, 2, 3, 4, 5)
    val seq: collection.Seq[Int] = simpleArray
    print(seq.reverse)
  }

  private def arrayToOpsTest(): Unit = {
    val simpleArray: Array[Int] = Array(1, 2, 3, 4, 5)
    val ops: collection.ArrayOps[Int] = simpleArray
    print(ops.reverse)
  }

  private def arrayToWrappedArrayTest(): Unit = {
    val simpleArray: Array[Int] = Array(1, 2, 3, 4, 5)
    val wrappedArray: WrappedArray[Int] = simpleArray
    print(wrappedArray.reverse)
  }

  simpleArrayTest()
  arrayToSequenceTest()
  arrayToOpsTest()
}
