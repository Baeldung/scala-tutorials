package com.baeldung.scala.slick_pg.entity

import org.json4s.JValue

import java.time.OffsetDateTime

case class BaeldungEntity(
  id: Long,
  createdAt: OffsetDateTime,
  prices: List[Double],
  metadata: JValue
)
