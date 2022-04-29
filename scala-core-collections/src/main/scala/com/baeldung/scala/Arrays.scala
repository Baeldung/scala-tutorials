package com.baeldung.scala

object Arrays extends App {

  val array: Array[Int] = Array(1, 2, 3)
  println(array(1))
  array(1) = 5
  println(array(1))

  val seq: Seq[Int] = array
  val newArray: Array[Int] = seq.toArray

  val arrayReversed: Array[Int] = array.reverse
  val seqReversed: Seq[Int] = seq.reverse

  val matrixFill = Array.fill(3, 2)(0)
  val matrixTabulate = Array.tabulate(3, 5) { case (_, c) => c }

  def matrix2String[T](matrix: Array[Array[T]]): String =
    matrix.map(_.mkString(" ")).mkString("\n")

  println(matrix2String(matrixFill))
  println("--")
  println(matrix2String(matrixTabulate))
}
