package com.baeldung.scala.doobie

import cats.effect.{IO, IOApp}
import doobie.implicits._
import doobie.{Fragment, Transactor}

object DoobieFragments extends IOApp.Simple {

  override def run: IO[Unit] = {
    val transactor: Transactor[IO] = Transactor.fromDriverManager[IO](
      "org.postgresql.Driver",
      "jdbc:postgresql://localhost:5432/world-db",
      "world",
      "world123"
    )

    import doobie.Fragments._
    val optionalCityNameParam: Option[String] = Some("%Pol%")
    val optionalCityNameFragment: Option[Fragment] = optionalCityNameParam.map(name => fr"name like $name")
    val limitFragment = fr"limit 5"

    (for {
      selectedCities <- (fr"select name from city" ++ whereAndOpt(optionalCityNameFragment)).query[String].to[List]
      selectedLimitedCities <- (fr"select name from city" ++ limitFragment).query[String].to[List]
    } yield (selectedCities, selectedLimitedCities)).transact(transactor).map { tuple =>
      println(s"fragment results: $tuple")
    }
  }
}

