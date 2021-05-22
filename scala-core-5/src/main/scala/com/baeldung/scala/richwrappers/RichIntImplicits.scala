package com.baeldung.scala.richwrappers

object RichIntImplicits {
  implicit class RichInt(wrapped: Int) {
    val digits: Int = wrapped.toString.length
  }
}
