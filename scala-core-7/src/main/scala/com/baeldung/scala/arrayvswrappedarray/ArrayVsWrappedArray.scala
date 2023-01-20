package com.baeldung.scala.arrayvswrappedarray

import scala.collection.mutable.WrappedArray
import scala.collection.ArrayOps

object ArrayVsWrappedArray extends App {
  private def printSimpleArray(): Unit = {
    val simpleArray: Array[Int] = Array(1, 2, 3, 4, 5)
    print(simpleArray)
  }

  private def convertArrayToSequence(): Unit = {
    val simpleArray: Array[Int] = Array(1, 2, 3, 4, 5)
    val seq: collection.Seq[Int] = simpleArray
    print(seq.reverse)
  }

  private def convertArrayToOps(): Unit = {
    val simpleArray: Array[Int] = Array(1, 2, 3, 4, 5)
    val ops: ArrayOps[Int] = simpleArray
    print(ops.reverse)
  }

  private def convertArrayToWrappedArray(): Unit = {
    val simpleArray: Array[Int] = Array(1, 2, 3, 4, 5)
    val wrappedArray: WrappedArray[Int] = simpleArray
    print(wrappedArray.reverse)
  }

  printSimpleArray()
  convertArrayToSequence()
  convertArrayToOps()
  convertArrayToWrappedArray()
}
