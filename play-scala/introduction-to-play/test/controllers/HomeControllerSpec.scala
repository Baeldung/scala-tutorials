package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._

class HomeControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

    "HomeController GET" should {

        "render the index page from a new instance of controller" in {
            val controller = new HomeController(stubControllerComponents())
            val home = controller.index().apply(FakeRequest(GET, "/"))

            status(home) mustBe OK
            contentType(home) mustBe Some("text/html")
            contentAsString(home) must include("Welcome to Introduction to Play Framework")
        }

        "render the index page from the application" in {
            val controller = inject[HomeController]
            val home = controller.index().apply(FakeRequest(GET, "/"))

            status(home) mustBe OK
            contentType(home) mustBe Some("text/html")
            contentAsString(home) must include("Welcome to Introduction to Play Framework")
        }

        "render the index page from the router" in {
            val request = FakeRequest(GET, "/")
            val home = route(app, request).get

            status(home) mustBe OK
            contentType(home) mustBe Some("text/html")
            contentAsString(home) must include("Welcome to Introduction to Play Framework")
        }

        "render a page that prints the sum of two numbers" in {
            val request = FakeRequest(GET, "/sum/10/20")
            val sumOfNumbers = route(app, request).get

            status(sumOfNumbers) mustBe OK
            contentType(sumOfNumbers) mustBe Some("text/html")
            contentAsString(sumOfNumbers) must include("The sum is 30!")
        }
    }
}
