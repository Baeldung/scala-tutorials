package scalatra.tutorial.servlet

import org.scalatra._

class MyScalatraServlet extends ScalatraServlet {

  get("/") {
    val env = getServletContext.getInitParameter("org.scalatra.environment")
    views.html.hello(env)
  }

}
