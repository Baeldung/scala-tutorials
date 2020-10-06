package com.baeldung.scala.exceptionhandling

sealed trait ValidationErrors extends Product with Serializable

object ValidationErrors {
  final case class IllegalLogin (login: String) extends ValidationErrors
  final case class IllegalPassword(password: String) extends ValidationErrors
}