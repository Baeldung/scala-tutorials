package com.baeldung.scala.exceptionhandling

import cats.data.ValidatedNel
import cats.syntax.apply._
import cats.syntax.validated._
import com.baeldung.scala.exceptionhandling.ValidationErrors.{
  IllegalLogin,
  IllegalPassword
}

object HandlingWithValidated {
  type InvalidOr[T] = ValidatedNel[ValidationErrors, T]

  val loginPattern = "^[a-zA-Z0-9]+$".r
  val passwordPattern =
    "(?=^.{10,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$".r

  def validateLogin(login: String): InvalidOr[String] =
    Option(login)
      .flatMap(loginPattern.findFirstIn(_))
      .map(_.validNel)
      .getOrElse(IllegalLogin(login).invalidNel)

  def validatePassword(password: String): InvalidOr[String] =
    Option(password)
      .flatMap(passwordPattern.findFirstIn(_))
      .map(_.validNel)
      .getOrElse(IllegalPassword(password).invalidNel)

  def validateUser(user: User): InvalidOr[User] = {
    (validateLogin(user.login), validatePassword(user.password)).mapN(User)
  }
}
