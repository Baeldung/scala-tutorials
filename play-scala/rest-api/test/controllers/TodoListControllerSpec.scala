package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.libs.json.{JsObject, JsString}
import play.api.test._
import play.api.test.Helpers._

class TodoListControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
  "TodoListController add new item" should {

    "should return 400 Bad Request if the Content-Type header is not set" in {
      val todoListItemJson = "{\"description\":\"some test description\"}"

      val controller = new TodoListController(stubControllerComponents())
      val addNewItem = controller.addNewItem().apply(
        FakeRequest(POST, "/todo").withTextBody(todoListItemJson)
      )

      status(addNewItem) mustBe BAD_REQUEST
    }

    "getAll should return the previously added task" in {
      val taskContent = "we expect to see this text in the response"
      val jsonObject = JsObject(Map("description" -> JsString(taskContent)))

      val controller = new TodoListController(stubControllerComponents())
      val addNewItem = controller.addNewItem().apply(
        FakeRequest(POST, "/todo")
          .withJsonBody(jsonObject)
          .withHeaders(
            FakeHeaders(Seq(("Content-type", "application-json")))
          )
      )

      status(addNewItem) mustBe CREATED

      val getAll = controller.getAll().apply(
        FakeRequest(GET, "/todo")
      )

      status(getAll) mustBe OK
      contentAsString(getAll) must include(taskContent)
    }
  }
}
