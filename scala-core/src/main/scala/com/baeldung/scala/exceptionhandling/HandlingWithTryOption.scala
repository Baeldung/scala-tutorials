package com.baeldung.scala.exceptionhandling

import scala.util.Try

object HandlingWithTryOption {
  def tryOptionAuthenticate (user: User): Try[Option[Session]] = {
    Try(HandlingWithOption.authenticateOptional(user))
  }

  def tryOptionGetResource (resourceId: String, session: Session): Try[Option[Resource]] = {
    Try(HandlingWithOption.getResourceOptional(resourceId, session))
  }

  def getTryOptionResourceValue (user: User, resourceId: String): Try[Option[String]] = {
    for {
      sessionOpt <- tryOptionAuthenticate(user)
      if sessionOpt.isDefined
      resource <- tryOptionGetResource(resourceId, sessionOpt.get)
    } yield {
      resource.map(_.value)
    }
  }
}
