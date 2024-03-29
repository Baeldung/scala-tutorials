package com.baeldung.scala.cats.writer

import cats.Id
import cats.data.{Writer, WriterT}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

final case class User(id: Long, name: String)
final case class Account(name: String, address: String, country: String)
class WriterMonadUnitTest extends AnyFlatSpec with Matchers {

  def getUser(token: String): User = User(1, "user-1")
  def getAccount(user: User): Account = Account("User 1", "Str 1", "Germany")

  it should "create a simple writer monad" in {
    val writer: Writer[String, Int] = Writer("Multiplication", 5 * 5)
    val (desc, value) = writer.run
    value shouldBe 25
    desc shouldBe "Multiplication"
  }

  it should "apply for-comprehension to multiple writer" in {
    val combined: Writer[String, Int] = for {
      writer1 <- Writer("Init Value,", 10)
      writer2 <- Writer("Multiplication", 5)
    } yield writer1 * writer2
    combined.run shouldBe ("Init Value,Multiplication", 50)
  }

  it should "create writer monad and execute it" in {
    val writer = Writer("Get User from token", getUser("token"))
    val expectedUser = User(1, "user-1")
    val (log, usr) = writer.run
    usr.name shouldBe expectedUser.name
    log shouldBe "Get User from token"
  }

  it should "add additional log to log side" in {
    val writer = Writer("Init Value", 100)
    val writer2 = writer.tell(",Starting manipulations")
    writer2.run._1 shouldBe "Init Value,Starting manipulations"
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

  it should "modify the value side using map function" in {
    val writer = Writer("Number", 5)
    val doubleIt = writer.map(_ * 2)
    val (log, value) = doubleIt.run
    value shouldBe 10
    log shouldBe "Number"
  }

  it should "clear the log side of writer" in {
    val writer = Writer("Init Value", 100)
    val resetWriter = writer.reset
    val (log, value) = resetWriter.run
    value shouldBe 100
    log shouldBe empty
  }

  it should "get the value from writer" in {
    val writer = Writer("Log Side", 100)
    val value: Int = writer.value
    value shouldBe 100
  }

  it should "swap the values in writer" in {
    val writer = Writer("Log Side", 100)
    val (log, value) = writer.swap.run
    log shouldBe 100
    value shouldBe "Log Side"
  }

  it should "extract the value as a tuple" in {
    val writer = Writer("Get User from token", getUser("token"))
    val (log, tuple) = writer.listen.run
    log shouldBe tuple._2
    tuple._1.name shouldBe "user-1"
  }

}
