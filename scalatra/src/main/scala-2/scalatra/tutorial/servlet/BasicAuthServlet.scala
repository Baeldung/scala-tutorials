package scalatra.tutorial.servlet

import org.scalatra.ScalatraServlet
import scalatra.tutorial.auth.AuthSupport

class BasicAuthServlet extends ScalatraServlet with AuthSupport {

  get("/") {
    basicAuth()
    "Basic auth protected page"
  }

}
