package scalatra.tutorial.servlet

import org.scalatra.{FutureSupport, ScalatraServlet}
import scalatra.tutorial.model.{DbSupport, UserRepo}
import slick.jdbc.H2Profile.api._

class DbServlet(val db: Database)
  extends ScalatraServlet
  with DbSupport
  with FutureSupport {

  post("/create-users") {
    db.run(UserRepo.users.schema.create)
  }

  post("/insert-users") {
    db.run(UserRepo.insertData)
  }

  post("/destroy-users") {
    db.run(UserRepo.users.schema.drop)
  }

}
