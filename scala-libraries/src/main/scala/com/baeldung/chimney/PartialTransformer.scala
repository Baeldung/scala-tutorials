package com.baeldung.chimney

import io.scalaland.chimney.*, dsl.*, partial.*

object ChimneyPartialTransformer extends App:

  val fn: Int => Boolean =
    case 0 => false
    case 1 => true
    case i => throw Exception(s"Provided integer invalid: $i")

  given PartialTransformer[Int, Boolean] =
    PartialTransformer.fromFunction(fn)

  val result: Result[Boolean] = 0.transformIntoPartial[Boolean]
