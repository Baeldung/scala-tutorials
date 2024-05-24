package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.Helpers._
import play.api.test._

class ViewTemplateControllerUnitTest
  extends PlaySpec
  with GuiceOneAppPerTest
  with Injecting {
  "ViewTemplateController should return view with expected elements" should {

    "should return two articles" in {
      val controller = new ViewTemplateController(stubControllerComponents())
      val result = controller
        .index
        .apply(
          FakeRequest(GET, "/template")
        )

      contentAsString(result) must include("Introduction to Play Framework")
      contentAsString(result) must include(
        "Building REST API in Scala with Play Framework"
      )
    }

    "with_class function returns the same two articles" in {
      val controller = new ViewTemplateController(stubControllerComponents())
      val result = controller
        .withClass
        .apply(
          FakeRequest(GET, "/withclass")
        )

      contentAsString(result) must include("Introduction to Play Framework")
      contentAsString(result) must include(
        "Building REST API in Scala with Play Framework"
      )
    }
  }
}
