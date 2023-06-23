package scalatra.tutorial.auth

import org.scalatra.ScalatraBase
import org.scalatra.auth.strategy.BasicAuthStrategy
import scalatra.tutorial.model.User

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import scala.util.Random

class CustomBasicAuthStrategy(
  protected override val app: ScalatraBase,
  realm: String
) extends BasicAuthStrategy[User](app, realm) {

  override protected def getUserId(user: User)(implicit
    request: HttpServletRequest,
    response: HttpServletResponse
  ): String = user.email

  override protected def validate(userName: String, password: String)(implicit
    request: HttpServletRequest,
    response: HttpServletResponse
  ): Option[User] = {
    if (userName == "user" && password == "pwd")
      Some(User(Random.nextLong(), "scalatra"))
    else None
  }
}
