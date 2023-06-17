package com.baeldung.scala.skunk

import cats.effect.{IO, IOApp}
import cats.effect.kernel.Resource
import skunk.{Query, Session}
import cats.effect._
import natchez.Trace.Implicits.noop
import skunk._
import skunk.implicits._
import skunk.codec.all._

object Skunk {
  case class User(id: Int, username: String, email: String, age: Int)

  def createSession(): Resource[IO, Session[IO]] =
    Session.single[IO](
      host = "localhost",
      user = "baeldung",
      database = "baeldung",
      password = Some("baeldung"),
      port = 5432
    )

  def getAllUsers(resource: Resource[IO, Session[IO]]) = {
    val mappedQuery: Query[Void, User] =
      sql"SELECT * FROM Users"
        .query(int4 *: varchar(255) *: varchar(255) *: int4)
        .to[User]

    val results: IO[List[User]] = resource.use(s => s.execute(mappedQuery))
    results
  }

  def getUserWithId(resource: Resource[IO, Session[IO]]) = {
    val userDecoder: Decoder[User] =
      (int4 *: varchar(255) *: varchar(255) *: int4).to[User]
    val query: Query[(Int *: String *: EmptyTuple), User] =
      sql"""
        SELECT * FROM Users WHERE
          id = $int4 AND username LIKE $varchar
       """
        .query(userDecoder)

    val preparedQuery =
      resource
        .flatMap(session => session.prepareR(query))

    preparedQuery.use(pq => pq.unique(1, "baeldungUser"))
  }

  def removeUserWithId(resource: Resource[IO, Session[IO]]) = {
    val command: Command[Void] =
      sql"""
        DELETE FROM Users WHERE
          id = 5
       """.command

    resource.use(session => session.execute(command))
  }

  def removePrepared(resource: Resource[IO, Session[IO]]) = {
    val command: Command[Int *: String *: EmptyTuple] =
      sql"""
        DELETE FROM Users WHERE
          id = $int4 and username = $varchar
       """.command
    resource
      .flatMap(session => session.prepareR(command))
      .use(pc => pc.execute((1, "baeldungUser")))
  }
}
