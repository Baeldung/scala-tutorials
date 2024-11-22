package com.baeldung.scala.slick_pg

import com.baeldung.scala.slick_pg.BaeldungPostgresProfile.api.*
import com.baeldung.scala.slick_pg.entity.BaeldungEntity
import com.baeldung.scala.slick_pg.table.baeldungEntityTable
import com.dimafeng.testcontainers.PostgreSQLContainer
import com.dimafeng.testcontainers.scalatest.TestContainerForAll
import org.json4s.native.JsonMethods
import org.scalatest.flatspec.AsyncFlatSpec
import org.testcontainers.utility.DockerImageName
import slick.dbio.DBIO

import java.time.OffsetDateTime
import scala.concurrent.Future

class BaeldungPostgresProfileSpec
  extends AsyncFlatSpec
  with TestContainerForAll {

  override val containerDef: PostgreSQLContainer.Def = PostgreSQLContainer.Def(
    dockerImageName = DockerImageName.parse("postgres:16.4"),
    databaseName = "baeldung",
    username = "baeldung",
    password = "baeldung"
  )

  "BaeldungPostgresProfile" should "insert entity" in {
    withContainers { container =>

      val db: BaeldungPostgresProfile.backend.Database =
        BaeldungPostgresProfile.backend.Database.forURL(
          containerDef.start().jdbcUrl,
          user = "baeldung",
          password = "baeldung",
          driver = "org.postgresql.Driver"
        )

      val createdAt = OffsetDateTime.now()
      val res = db
        .run(
          DBIO.seq(
            baeldungEntityTable.schema.createIfNotExists,
            baeldungEntityTable += BaeldungEntity(
              1L,
              createdAt,
              List(1d, 5d, 2.24d),
              JsonMethods.parse("""{"field1": 5, "field2": "test"}""")
            )
          )
        )
        .flatMap(_ => db.run(baeldungEntityTable.result))

      for (sequence <- res) yield {
        sequence.foreach(entity => {
          assert(entity.id.equals(1L))
          assert(entity.createdAt.equals(createdAt))
          assert(entity.prices.equals(List(1d, 5d, 2.24d)))
          assert(
            entity.metadata.equals(
              JsonMethods.parse("""{"field1": 5, "field2": "test"}""")
            )
          )
        })
        succeed
      }
    }
  }
}
