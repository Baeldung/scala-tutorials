package com.baeldung.scala.richwrappers

import scala.language.implicitConversions

object SimpleRichInt {
  implicit final def SimpleRichInt(n: Int): SimpleRichInt = new SimpleRichInt(n)
}

class SimpleRichInt(wrapped: Int) {
  val digits: Int = wrapped.toString.length
}
