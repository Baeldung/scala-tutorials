package scalatra.tutorial.model

import slick.dbio.Effect
import slick.lifted.Tag
import slick.jdbc.H2Profile.api._
import slick.sql.FixedSqlStreamingAction

case class User(id: Long, email: String)

object UserRepo {

  class Users(tag: Tag) extends Table[User](tag, "user") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def email = column[String]("email")
    override def * = (id, email) <> (User.tupled, User.unapply)
  }

  val users = TableQuery[Users]

  def all = users.result

  def insertData =
    DBIO.seq(
      users += User(1L, "user1@example.com"),
      users += User(2L, "user2@example.com"),
      users += User(3L, "user3@example.com"),
      users += User(4L, "user4@example.com")
    )

  def insert(user: User) = users += user
}
