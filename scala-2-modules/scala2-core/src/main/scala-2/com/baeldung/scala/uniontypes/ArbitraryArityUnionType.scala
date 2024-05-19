package com.baeldung.scala.uniontypes

import scala.language.implicitConversions

object ArbitraryArityUnionType extends App {

  def isIntOrStringOrBool[T: IntOrStringOrBool](t: T): String = t match {
    case i: Int     => "%d is an Integer".format(i)
    case s: String  => "%s is a String".format(s)
    case b: Boolean => "%b is a Boolean".format(b)
  }

  sealed trait IntOrStringOrBool[T]
  object IntOrStringOrBool {
    implicit val intInstance: IntOrStringOrBool[Int] =
      new IntOrStringOrBool[Int] {}
    implicit val strInstance: IntOrStringOrBool[String] =
      new IntOrStringOrBool[String] {}
    implicit val boolInstance: IntOrStringOrBool[Boolean] =
      new IntOrStringOrBool[Boolean] {}
  }
  println(isIntOrStringOrBool(10)) // prints "10 is an Integer"
  println(isIntOrStringOrBool("hello")) // prints "hello is a String"
  println(isIntOrStringOrBool(true)) // prints "true is a Boolean"

  sealed trait AOrB[A, B]
  object AOrB {
    implicit def aInstance[A, B](a: A): AOrB[A,B] = new AOrB[A, B] {}
    implicit def bInstance[A, B](b: B): AOrB[A,B] = new AOrB[A, B] {}
  }

  def isIntOrString[T <% String AOrB Int](t: T): String = t match {
    case i: Int    => "%d is an Integer".format(i)
    case s: String => "%s is a String".format(s)
  }

  println(isIntOrString(10)) // prints "10 is an Integer"
  println(isIntOrString("hello")) // prints "hello is a String"
}
