package guice.services

import models.User

class UserService {

  def getAll(): Seq[User] = {
    Seq(
      User(1L, "John Doe"),
      User(2L, "Sam Doe")
    )
  }

}
