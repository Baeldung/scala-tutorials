package com.baeldung.scala.uniontypes

object ArbitraryArityUnionType extends App {

  def isIntOrStringOrBool[T: IntOrStringOrBool](t: T): String = t match {
    case i: Int     => "%d is an Integer".format(i)
    case s: String  => "%s is a String".format(s)
    case b: Boolean => "%b is a Boolean".format(b)
  }

  implicit val intInstance: IntOrStringOrBool[Int] =
    new IntOrStringOrBool[Int] {}
  implicit val strInstance: IntOrStringOrBool[String] =
    new IntOrStringOrBool[String] {}
  implicit val boolInstance: IntOrStringOrBool[Boolean] =
    new IntOrStringOrBool[Boolean] {}

  sealed trait IntOrStringOrBool[T]

  println(isIntOrStringOrBool(10))
  println(isIntOrStringOrBool("hello"))
  println(isIntOrStringOrBool(true))

}
