package com.baeldung.services

import com.baeldung.models.User

class UserService {

  def getAll(): Seq[User] = {
    Seq(
      User(1L, "John Doe"),
      User(2L, "Sam Doe")
    )
  }

}
