package com.baeldung.scala.exceptionhandling

import scala.util.Try

object HandlingWithTry {
  def tryAuthenticate(user: User): Try[Session] = {
    Try(LegacyService.authenticate(user))
  }

  def tryGetResource(resourceId: String, session: Session): Try[Resource] = {
    Try(LegacyService.getResource(resourceId, session))
  }

  def tryGetResourceValue(user: User, resourceId: String): Try[String] = {
    for {
      session <- tryAuthenticate(user)
      resource <- tryGetResource(resourceId, session)
    } yield {
      resource.value
    }
  }
}
