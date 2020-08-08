package com.baeldung.scala.exceptionhandling

object HandlingWithOption {
  def authenticateOptional(user: User): Option[Session] = {
    Option(LegacyService.authenticate(user))
  }

  def getResourceOptional(resourceId: String, session: Session): Option[Resource] = {
    Option(LegacyService.getResource(resourceId, session))
  }
}
