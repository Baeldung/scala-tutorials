package com.baeldung.scala.concurrency

import java.math.BigInteger
import java.net.URL
import java.security.MessageDigest

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future, Promise}
import scala.util.control.NonFatal
import scala.util.{Failure, Success}

object ScalaAndPromise {
  def sampleFuture(): Future[Int] = Future {
    println("Long running computation started.")
    val result = {
      Thread.sleep(5)
      5
    }
    println("Our computation, finally finished.")
    result
  }

  type Name = String
  type Email = String
  type Password = String
  type Avatar = URL

  case class User(name: Name, email: Email, password: Password, avatar: Avatar)

  def exist(email: Email): Future[Boolean] = Future {
    Thread.sleep(100) // Call to the database takes time
    true
  }

  def md5hash(str: String): String =
    new BigInteger(1,
      MessageDigest
        .getInstance("MD5")
        .digest(str.getBytes)
    ).toString(16)

  def avatar(email: Email): Future[Avatar] = Future {
    Thread.sleep(200) // Call to the database takes time
    new Avatar("http://avatar.example.com/user/23k520f23f4.png")
  }

  def createUser(name: Name, email: Email, password: Password): Future[User] =
    for {
      _ <- exist(email)
      avatar <- avatar(email)
      hashedPassword = md5hash(password)
    } yield User(name, email, hashedPassword, avatar)

  def runByPromise[T](block: => T)(implicit ec: ExecutionContext): Future[T] = {
    val p = Promise[T]()
    ec.execute { () =>
      try {
        p.success(block)
      } catch {
        case NonFatal(e) => p.failure(e)
      }
    }
    p.future
  }
}

object FutureAndPromiseApp extends App {

  import ScalaAndPromise._

  // Access to the value of Future by passing callback to the onComplete
  val userFuture: Future[User] = createUser("John", "John@emaple.com", "secret")
  userFuture.onComplete {
    case Success(user) =>
      println(s"User created: $user")
    case Failure(exception) =>
      println(s"Creating user failed due to the exception: $exception")
  }

  // Access to the value of Future by applying the result function on the Future value
  val user: User = Await.result(userFuture, Duration.Inf)

  // Forcing the Future value to be complete
  val completedFuture: Future[User] = Await.ready(userFuture, Duration.Inf)
  completedFuture.value.get match {
    case Success(user) =>
      println(s"User created: $user")
    case Failure(exception) =>
      println(s"Creating user failed due to the exception: $exception")
  }

}
