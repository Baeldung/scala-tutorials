package scalatra.tutorial

import org.scalatra.test.scalatest._
import scalatra.tutorial.servlet.MyScalatraServlet

class MyScalatraServletUnitTest extends ScalatraFunSuite {

  addServlet(classOf[MyScalatraServlet], "/*")

  test("GET / on MyScalatraServlet should return status 200") {
    get("/") {
      status should equal(200)
    }
  }

}
