package com.baeldung.scala.tailrec
import scala.annotation.tailrec

object StringLength {

    def recursiveLength(list: List[String]): Long = list match {
        case Nil => 0
        case head :: tail => 1 + recursiveLength(tail)
    }

    def recursiveLengthVerbose(list: List[String]): Long = list match {
        case Nil => 0
        case head :: tail => {
            val accumulator = recursiveLengthVerbose(tail)
            1 + accumulator
        }
    }

    @tailrec
    def tailRecursiveLength(list: List[String], accumulator: Long): Long = list match {
        case Nil => accumulator
        case head :: tail => tailRecursiveLength(tail, accumulator + 1)
    }
}
