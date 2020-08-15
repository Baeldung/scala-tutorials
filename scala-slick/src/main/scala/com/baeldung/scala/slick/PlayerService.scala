package com.baeldung.scala.slick

import java.time.LocalDate

import com.baeldung.scala.slick.SlickTables.PlayerTable
import slick.jdbc.H2Profile.api._
import slick.lifted.TableQuery

import scala.concurrent.Future

class PlayerService {

  lazy val db          = Connection.db
  lazy val playerTable = TableQuery[PlayerTable]

  def createTable: Future[Int] = {
    val createQuery =
      sqlu"""create table "Player"(
         "player_id" bigserial primary key,
         "name" varchar not null,
         "country" varchar not null,
         "dob" date
        ) """

    db.run(createQuery)
  }

  def insertPlayer(player: Player): Future[Int] = {
    val insertPlayerQuery = playerTable += player
    db.run(insertPlayerQuery)
  }

  def insertPlayers(players: Seq[Player]): Future[Option[Int]] = {
    val insertPlayerQuery = playerTable ++= players
    db.run(insertPlayerQuery)
  }

  def getAllPlayers: Future[Seq[Player]] = {
    db.run(playerTable.result)
  }

  def filterByName(name: String): Future[Seq[Player]] = {
    db.run[Seq[Player]](playerTable.filter(_.name === name).result)
  }

  def filterByCountry(country: String): Future[Seq[Player]] = {
    val filterQuery = playerTable.filter(_.country === country)
    db.run[Seq[Player]](filterQuery.result)
  }

  def updateDob(playerId: Long, dob: LocalDate): Future[Int] = {
    val updateAction = playerTable.filter(_.id === playerId).map(_.dob).update(Some(dob))
    db.run(updateAction)
  }

  def deleteByName(name: String): Future[Int] = {
    val deleteAction = playerTable.filter(_.name === name).delete
    db.run(deleteAction)
  }

}
