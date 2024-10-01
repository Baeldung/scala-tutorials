package com.baeldung.chimney

import io.scalaland.chimney.dsl.*

object ChimneyPatcher extends App:

  case class User(id: Int, email: Option[String], phone: Option[Long])

  case class UserUpdateForm(email: String, phone: Long)

  val user = User(10, Some("abc@@domain.com"), Some(1234567890L))
  val updateForm = UserUpdateForm("xyz@@domain.com", 123123123L)

  val patchedValue: User = user.patchUsing(updateForm)

  // Standard Library Alternative

  case class SimpleCaseClass(a: Int, b: Int)

  val simpleClass = SimpleCaseClass(0, 0)

  simpleClass.copy(b = 2) // SimpleCaseClass(0, 2)
