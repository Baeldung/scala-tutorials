package com.baeldung.scala.skunk

import cats.effect.{IO, IOApp}
import cats.effect.kernel.Resource
import skunk.{Query, Session}
import cats.effect._
import natchez.Trace.Implicits.noop
import skunk._
import skunk.implicits._
import skunk.codec.all._

object Skunk{
  case class User(id: Int, username: String, email: String, age:Int)

  def createSession(): Resource[IO, Session[IO]] =
    Session.single[IO]( host = "localhost",
      user = "baeldung",
      database = "baeldung",
      password = Some("baeldung"),
      port = 5432
    )

  def getAllUsers(resource: Resource[IO, Session[IO]]) = {
    val query: Query[Void, Int ~ String ~ String ~ Int] =
      sql"SELECT * FROM Users"
      .query(int4 ~ varchar(255) ~ varchar(255) ~ int4)

    val mappedQuery: Query[Void, User] = query
      .gmap[User]
    val results: IO[List[User]] = resource.use(s => s.execute(mappedQuery))
    results
  }

  def getUserWithId(resource: Resource[IO, Session[IO]]) = {
    val query:Query[Int ~ String, User]  =
      sql"""
        SELECT * FROM Users WHERE
          id = $int4 AND username LIKE $varchar
       """
      .query(int4 ~ varchar(255) ~ varchar(255) ~ int4)
      .gmap[User]

   val preparedQuery: Resource[IO, PreparedQuery[IO, Int ~ String, User]] =  resource
      .flatMap(session => session.prepare(query))

    preparedQuery.use(pq => pq.unique(1, "baeldungUser"))
  }

  def removeUserWithId(resource: Resource[IO, Session[IO]]) = {
    val command: Command[Void]  =
      sql"""
        DELETE FROM Users WHERE
          id = 5
       """
        .command

    resource.use(session => session.execute(command))
  }

  def removePrepared(resource: Resource[IO, Session[IO]]) = {
    val command: Command[Int ~ String]  =
      sql"""
        DELETE FROM Users WHERE
          id = $int4 and username = $varchar
       """
        .command
    resource.flatMap(session => session.prepare(command))
      .use(pc => pc.execute((1, "baeldungUser")))
  }
}
