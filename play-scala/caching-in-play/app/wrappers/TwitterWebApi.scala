package wrappers

import javax.inject.Inject
import play.api.Configuration
import play.api.http.Status._
import play.api.http.{ HeaderNames, MimeTypes }
import play.api.libs.json.JsValue
import play.api.libs.ws.WSClient

import scala.concurrent.{ ExecutionContext, Future }

class TwitterWebApi @Inject()(
    wsClient: WSClient,
    configuration: Configuration,
    implicit val executionContext: ExecutionContext
) {

  val bearerToken: String = configuration.get[String]("twitter.bearerToken")
  val recentSearchUrl: String = configuration.get[String]("twitter.recentSearchUrl")

  def recentSearch(fromTwitterUser: String): Future[JsValue] = {
    val url = String.format(recentSearchUrl, fromTwitterUser)
    wsClient
      .url(url)
      .withHttpHeaders(
        HeaderNames.ACCEPT -> MimeTypes.JSON,
        HeaderNames.AUTHORIZATION -> s"Bearer $bearerToken"
      ).get()
      .map { response =>
        if (response.status == OK) {
          response.json
        } else {
          throw ApiError(response.status, Some(response.statusText))
        }
      }
  }
}
