package com.baeldung.scala.tailrec
import scala.annotation.tailrec

object StringLength {

  def recursiveLength(list: List[String]): Long = list match {
    case Nil       => 0
    case _ :: tail => 1 + recursiveLength(tail)
  }

  def recursiveLengthVerbose(list: List[String]): Long = list match {
    case Nil => 0
    case _ :: tail => {
      val accumulator = recursiveLengthVerbose(tail)
      1 + accumulator
    }
  }

  @tailrec
  def tailRecursiveLength(list: List[String], accumulator: Long): Long =
    list match {
      case Nil       => accumulator
      case _ :: tail => tailRecursiveLength(tail, accumulator + 1)
    }
}
