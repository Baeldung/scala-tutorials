package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._

class ArticleControllerUnitTest
  extends PlaySpec
  with GuiceOneAppPerTest
  with Injecting {

  "ArticleController GET" should {

    "render the index page from a new instance of controller" in {
      val controller = new ArticleController(stubControllerComponents())
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include(
        "This header is styled using LESS"
      )
    }

    "render the index page from the application" in {
      val controller = inject[ArticleController]
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include(
        "This header is styled using LESS"
      )
    }

    "render the index page from the router" in {
      val request = FakeRequest(GET, "/")
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include(
        "This header is styled using LESS"
      )
    }

    "render a page that greets someone" in {
      val request = FakeRequest(GET, "/greets/eugen")
      val greets = route(app, request).get

      status(greets) mustBe OK
      contentType(greets) mustBe Some("text/html")
      contentAsString(greets) must include("Greetings, dear eugen!")
    }
  }
}
