package com.baeldung.scala.slick_pg.table

import com.baeldung.scala.slick_pg.BaeldungPostgresProfile.BaeldungApi.*
import com.baeldung.scala.slick_pg.entity.BaeldungEntity
import org.json4s.JValue

import java.time.OffsetDateTime

class BaeldungEntityTable(tag: Tag)
  extends Table[BaeldungEntity](tag, None, "baeldung_entity") {

  val id = column[Long]("id", O.PrimaryKey, O.AutoInc, O.SqlType("BIGSERIAL"))
  val createdAt = column[OffsetDateTime]("created_at", O.SqlType("TIMESTAMPTZ"))
  val prices = column[List[Double]]("prices", O.SqlType("DOUBLE PRECISION[]"))
  val metadata = column[JValue]("metadata", O.SqlType("JSONB"))

  override def * = (id, createdAt, prices, metadata).mapTo[BaeldungEntity]

}

val baeldungEntityTable = TableQuery[BaeldungEntityTable]
