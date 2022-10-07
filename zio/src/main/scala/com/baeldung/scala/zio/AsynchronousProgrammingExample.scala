package com.baeldung.scala.zio

import zio._

case class User()

case class AuthError()

object LegacyAPI {
  def login(onSuccess: User => Unit, onFailure: AuthError => Unit): Unit = ???
}


object AsynchronousAPI {
  val login: ZIO[Any, AuthError, User] =
    ZIO.async[Any, AuthError, User] { callback =>
      LegacyAPI.login(
        user => callback(ZIO.succeed(user)),
        err => callback(ZIO.fail(err))
      )
    }
}
