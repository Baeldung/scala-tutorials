package com.baeldung.scala.listtotuple

object ConvertListToTuple {
  def twoElementsToTuple(list: List[String]): (String, String) = {
    val first :: second :: _ = list
    (first, second)
  }

  def twoElementsToTupleUsingMatch(list: List[String]): (String, String) = {
    list match {
      case first :: second :: _ => (first, second)
      case _                    => ("", "")
    }
  }

  def unknownSizeToTuple(list: List[String]): Tuple = {
    list match {
      case first :: second :: third :: _ => (first, second, third)
      case first :: second :: _          => (first, second)
      case _                             => ("", "")
    }
  }
}
