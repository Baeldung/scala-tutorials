package com.baeldung.scala.cats.show

import cats.implicits._

object InterfaceSyntax extends App {
  val shownInt: String = 123.show
  val shownString: String = "abc".show

  println(shownInt)
  println(shownString)
}
