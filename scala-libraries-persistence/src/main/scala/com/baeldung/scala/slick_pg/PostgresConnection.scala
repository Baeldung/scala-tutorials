package com.baeldung.scala.slick_pg

import com.baeldung.scala.slick_pg
import com.baeldung.scala.slick_pg.BaeldungPostgresProfile.api.*

object PostgresConnection {

  val db: slick_pg.BaeldungPostgresProfile.backend.Database =
    Database.forConfig("postgres")
}
