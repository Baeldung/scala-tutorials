package com.baeldung.scala.collections
import scala.collection.parallel.CollectionConverters._
object FoldingLists extends App {

  case class Person(name: String, sex: String)

  val parallelNumSeq = List(1, 2, 3, 4, 5).par
  val foldResult = parallelNumSeq.fold(0) { (acc1, acc2) =>
    val sum = acc1 + acc2
    println(s"Fold: acc1($acc1) + acc2($acc2) = $sum")
    sum
  }
  println(foldResult)

  val foldLeftResult =
    parallelNumSeq.foldLeft(0) { (acc, currNum) =>
      val sum = acc + currNum
      println(s"FoldLeft: acc($acc) + currNum($currNum) = $sum ")
      sum
    }
  println(foldLeftResult)

  val foldRightResult =
    parallelNumSeq.foldRight(0) { (currNum, acc) =>
      val sum = acc + currNum
      println(s"FoldRight: acc($acc) + currNum($currNum) = $sum")
      sum
    }
  println(foldRightResult)
}
