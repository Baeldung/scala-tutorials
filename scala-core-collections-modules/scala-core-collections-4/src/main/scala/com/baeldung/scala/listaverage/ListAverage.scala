package com.baeldung.scala.listaverage

object ListAverage {


  def naive(lst: List[Int]): Int  = {
    var total = 0
    var nrElements = 0

    for(elem <- lst) {
      total += elem
      nrElements += 1
    }

    total / nrElements
  }


  def averageDouble(lst: List[Int]): Double = {
    var total = 0

    // here we use double to ensure we dont round final number
    var nrElements = 0.0

    for (elem <- lst) {
      total += elem
      nrElements += 1
    }

    total / nrElements
  }

  def averageWithListMethods(lst: List[Int]): Int = {
    lst.sum / lst.size
  }

  def averageWithListMethodsDouble(lst: List[Int]): Double = {
    lst.sum / lst.size.toDouble
  }

  def averageWithFold(lst: List[Int]): Double = {
    val (sum, size) = lst.foldLeft((0.0, 0))((pair, elem)=>(pair._1 + elem, pair._2 + 1))
    sum / size
  }
}
