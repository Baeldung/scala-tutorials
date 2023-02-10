package com.baeldung.scala.cats.writer

import cats.data.Writer
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

final case class User(id:Long, name: String)
final case class Account(name:String, address: String, country: String)
class WriterMonadUnitTest extends AnyFlatSpec with Matchers {

  def getUser(token: String): User = User(1,"user-1")
  def getAccount(user: User): Account = Account("User 1", "Str 1", "Germany")

  it should "create writer monad and execute it" in {
    val writer = Writer("Get User from token", getUser("token"))
    val expectedUser = User(1, "user-1")
    val (log, usr) = writer.run
    usr.name shouldBe expectedUser.name
    log shouldBe "Get User from token"
  }

  it should "add additional log to log side" in {
    val writer = Writer("Get User from token", getUser("token"))
    val expectedUser = User(1, "user-1")
    val writer2 = writer.tell(", Added something more")
    val (log, usr) = writer.run
    usr.name shouldBe expectedUser.name
    log shouldBe "Get User from token"
    writer2.run._1 shouldBe "Get User from token, Added something more"
  }

  it should "use a list as log side" in {
    val writer = Writer(List("Get User from token"), getUser("token"))
    val expectedUser = User(1, "user-1")
    val writer2 = writer.tell(List("Added as list"))
    val (log, usr) = writer.run
    usr.name shouldBe expectedUser.name
    log shouldBe List("Get User from token")
    writer2.run._1 should have size 2
    writer2.run._1 shouldBe List("Get User from token", "Added as list")
  }

  it should "combine different writers together" in {
    val combinedWriter = for {
      user <- Writer("Get User from token", getUser("token"))
      account <- Writer(",Getting account info", getAccount(user))
      _ <- Writer.tell(",completed")
    } yield account


    val (logs, acc) = combinedWriter.run

    logs shouldBe "Get User from token,Getting account info,completed"
    acc.name shouldBe "User 1"

  }

}
