package com.baeldung.scala.exceptionhandling

import cats.MonadError // for MonadError
import cats.syntax.flatMap._
import cats.syntax.functor._

import scala.util.{Failure, Success, Try}
import com.baeldung.scala.exceptionhandling.LegacyErrors.{
  ResourceNotFound,
  ServerError,
  UserNotFound
}

object HandlingWithMonadError {
  def monadErrorAuthenticate[F[_], E](user: User)(implicit
    me: MonadError[F, E],
    adoptError: LegacyErrors => E
  ): F[Session] = {
    Try(LegacyService.authenticate(user)) match {
      case Failure(exception) => me.raiseError(ServerError(exception))
      case Success(null)      => me.raiseError(UserNotFound(user))
      case Success(session)   => me.pure(session)
    }
  }

  def monadErrorGetResource[F[_], E](resourceId: String, session: Session)(
    implicit
    me: MonadError[F, E],
    adoptError: LegacyErrors => E
  ): F[Resource] = {
    Try(LegacyService.getResource(resourceId, session)) match {
      case Failure(exception) => me.raiseError(ServerError(exception))
      case Success(null)      => me.raiseError(ResourceNotFound(resourceId))
      case Success(resource)  => me.pure(resource)
    }
  }

  def getResourceValue[F[_], E](user: User, resoureceId: String)(implicit
    me: MonadError[F, E],
    adoptError: LegacyErrors => E
  ): F[String] = {
    for {
      session <- monadErrorAuthenticate(user)
      resource <- monadErrorGetResource(resoureceId, session)
    } yield resource.value
  }
}
