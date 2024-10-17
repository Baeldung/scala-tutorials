package com.baeldung.chimney

import io.scalaland.chimney.*, dsl.*, partial.*

object ChimneyCodec extends App:

  case class Domain(a: Int, b: String)
  case class Dto(b: Option[String], a: Option[Int])

  given Codec[Domain, Dto] = Codec.derive
