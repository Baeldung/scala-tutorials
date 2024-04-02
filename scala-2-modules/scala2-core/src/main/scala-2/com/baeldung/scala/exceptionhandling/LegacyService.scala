package com.baeldung.scala.exceptionhandling

import java.io.IOException

object LegacyService {
  def authenticate(user: User): Session = user match {
    case null                  => null
    case User("root", _)       => throw new IllegalArgumentException
    case User(login, password) => Session(s"""${login}_session""", 3600)
  }

  def getResource(recourceId: String, session: Session): Resource =
    recourceId match {
      case null => null
      case "/"  => throw new IOException
      case _    => Resource(recourceId, "(:")
    }
}
