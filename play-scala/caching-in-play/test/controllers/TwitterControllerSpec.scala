package controllers

import org.mockito.Mockito.when
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.http.MimeTypes
import play.api.libs.json.{ JsValue, Json }
import play.api.test.Helpers._
import play.api.test._
import services.TwitterSearchService

import scala.concurrent.{ ExecutionContext, Future }

/**
  * Test our Twitter WS endpoint
  */
class TwitterControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting with MockitoSugar {
  implicit val executionContext: ExecutionContext = ExecutionContext.Implicits.global
  val twitterDevJsonResponse: JsValue = Json.parse(
    """
      |{
      |  "data": [
      |    {
      |      "text": "👋 Friendly reminder that some Twitter Developer Labs v1 endpoints will be retired next Monday, October 12! Details: https://t.co/zZ4LIsd9yC",
      |      "created_at": "2020-10-06T22:34:20.000Z",
      |      "id": "1313608555757355009",
      |      "author_id": "2244994945"
      |    },
      |    {
      |      "text": "Learn how to analyze Twitter data in R https://t.co/0thzkxbXZp",
      |      "created_at": "2020-10-05T17:47:47.000Z",
      |      "id": "1313174055764160512",
      |      "author_id": "2244994945"
      |    },
      |    {
      |      "text": "📊Learn how to get started with analyzing past conversations with the recent search endpoint in the new #TwitterAPI \n\nhttps://t.co/noOeqZKI3m",
      |      "created_at": "2020-09-30T16:47:38.000Z",
      |      "id": "1311346979243397121",
      |      "author_id": "2244994945"
      |    }
      |  ],
      |  "includes": {
      |    "users": [
      |      {
      |        "description": "The voice of the #TwitterDev team and your official source for updates, news, and events, related to the #TwitterAPI.",
      |        "name": "Twitter Dev",
      |        "username": "TwitterDev",
      |        "id": "2244994945"
      |      }
      |    ]
      |  },
      |  "meta": {
      |    "newest_id": "1313608555757355009",
      |    "oldest_id": "1311346979243397121",
      |    "result_count": 3
      |  }
      |}
      |""".stripMargin
  )
  "TwitterController" should {
    "return the expected search result from Twitter" in {
      val twitterSearchService = mock[TwitterSearchService]
      when(twitterSearchService.searchRecent("TwitterDev")).thenReturn(
        Future(twitterDevJsonResponse.as[Map[String, JsValue]])
      )
      val controller = new TwitterController(
        twitterSearchService,
        stubControllerComponents(),
        executionContext
      )
      val twitterSearchResults = controller.recentSearch("TwitterDev").apply(FakeRequest(GET, "/"))

      status(twitterSearchResults) mustBe OK
      contentType(twitterSearchResults) mustBe Some(MimeTypes.JSON)
      val json = contentAsJson(twitterSearchResults)
      assert(
        json.as[Map[String, JsValue]].getOrElse("data", throw new AssertionError()).as[List[JsValue]].head ==
          Json.parse(
            """
              |{
              |      "text": "👋 Friendly reminder that some Twitter Developer Labs v1 endpoints will be retired next Monday, October 12! Details: https://t.co/zZ4LIsd9yC",
              |      "created_at": "2020-10-06T22:34:20.000Z",
              |      "id": "1313608555757355009",
              |      "author_id": "2244994945"
              |}
              |""".stripMargin
          )
      )
    }
  }
}
