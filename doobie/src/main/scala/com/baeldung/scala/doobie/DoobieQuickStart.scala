package com.baeldung.scala.doobie

import cats.effect.{IO, IOApp}
import doobie.implicits._
import doobie.{ConnectionIO, Transactor}

// Equivalent to world db city table
case class City(id: Long, name: String, countryCode: String, district: String, population: Int)

object DoobieQuickStart extends IOApp.Simple {

  override def run: IO[Unit] = {
    val transactor: Transactor[IO] = Transactor.fromDriverManager[IO](
      "org.postgresql.Driver",
      "jdbc:postgresql://localhost:5432/world-db",
      "world",
      "world123"
    )

    val operations: ConnectionIO[Unit] = for {
      someCities <- sql"select id, name, country_code, district, population from city limit 5"
        .query[City]
        .to[List]
      _ = println(s"Selected some cities: $someCities")
      baeldungCity = City(5000, "Baeldung", "NLD", "Baeldungland", 1337)
      insertedId <- sql"insert into city (id, name, country_code, district, population) values (${baeldungCity.id}, ${baeldungCity.name}, ${baeldungCity.countryCode}, ${baeldungCity.district}, ${baeldungCity.population})"
        .update
        .withUniqueGeneratedKeys[Int]("id")
      _ = println(s"Inserted baeldung city: $insertedId")
      selectedCity <- sql"select id, name, country_code, district, population from city where id = $insertedId"
        .query[City]
        .unique
      _ = println(s"Selected baeldung city: $selectedCity")
      updatedName <- sql"update city set name = 'DungBael' where id = 5000"
        .update
        .withUniqueGeneratedKeys[String]("name")
      _ = println(s"Updated baeldung city: ${baeldungCity.name} -> $updatedName")
      deletedRows <- sql"delete from city where id = ${baeldungCity.id}"
        .update
        .run
      _ = println(s"Deleted baeldung city: $deletedRows")
    } yield ()

    operations.transact(transactor)
  }
}
