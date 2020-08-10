package com.baeldung.scala.exceptionhandling

sealed trait LegacyErrors extends Product with Serializable

object LegacyErrors {
  final case class ServerError(ex: Throwable) extends LegacyErrors
  final case class UserNotFound(user: User) extends LegacyErrors
  final case class ResourceNotFound(resourceId: String) extends LegacyErrors
}
