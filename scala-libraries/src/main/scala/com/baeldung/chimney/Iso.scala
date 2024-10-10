package com.baeldung.chimney

import io.scalaland.chimney.*, dsl.*, partial.*

object ChimneyIso extends App:

  case class StructuredItem(uuid: java.util.UUID)
  case class DomainItem(uuid: java.util.UUID)

  given Iso[StructuredItem, DomainItem] = Iso.derive
