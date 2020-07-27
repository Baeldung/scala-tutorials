package com.baeldung.scala.exceptionhandling

import scala.util.{Failure, Success, Try}

object HandlingWithEither {
  sealed trait LegacyErrors extends Product with Serializable
  final case class ServerError(ex: Throwable) extends LegacyErrors
  final case class UserNotFound(user: User) extends LegacyErrors
  final case class ResourceNotFound(resourceId: String) extends LegacyErrors

  def eitherAuthenticate (user: User): Either[LegacyErrors, Session] = {
    Try(LegacyService.authenticate(user)) match {
      case Failure(exception) => Left(ServerError(exception))
      case Success(null) => Left(UserNotFound(user))
      case Success(session) => Right(session)
    }
  }

  def eitherGetResource (resourceId: String, session: Session): Either[LegacyErrors, Resource] = {
    Try(LegacyService.getResource(resourceId, session)) match {
      case Failure(exception) => Left(ServerError(exception))
      case Success(null) => Left(ResourceNotFound(resourceId))
      case Success(resource) => Right(resource)
    }
  }

  def eitherGetResourceValue (user: User, resourceId: String): Either[LegacyErrors, String] = {
    for {
      session <- eitherAuthenticate(user)
      resource <- eitherGetResource(resourceId, session)
    } yield {
      resource.value
    }
  }
}
