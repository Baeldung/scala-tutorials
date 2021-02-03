package com.baeldung.scala.uniontypes

object EitherDisjointUnion extends App {

  def isIntOrString(t: Either[Int, String]): String = {
    t match {
      case Left(i)  => "%d is an Integer".format(i)
      case Right(s) => "%s is a String".format(s)
    }
  }

  println(isIntOrString(Left(10)))
  println(isIntOrString(Right("hello")))
}
