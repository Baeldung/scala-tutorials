package scalatra.tutorial.servlet

import org.json4s._
import org.scalatra.json.JacksonJsonSupport
import org.scalatra.{NoContent, ScalatraServlet}
import scalatra.tutorial.model.{DbSupport, User, UserRepo}
import slick.jdbc.H2Profile.api._

class UserServlet(val db: Database)
  extends ScalatraServlet
  with JacksonJsonSupport
  with DbSupport {

  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  before() {
    enrichSession(session)
    contentType = formats("json")
  }

  get("/") {
    db.run(UserRepo.all)
  }

  post("/:userId") {
    val userId = params("userId")
    val body = parsedBody.extract[User]
    log(s"userId: $userId, body: $body")
    db.run(UserRepo.insert(body))
      .map(_ => NoContent)
  }

}
