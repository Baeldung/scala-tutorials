package controllers

import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._
import services.TwitterSearchService

import scala.concurrent.ExecutionContext

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class TwitterController @Inject()(
    twitterSearchService: TwitterSearchService,
    override val controllerComponents: ControllerComponents,
    implicit val executionContext: ExecutionContext
) extends BaseController {

  /**
    * Create an Action to search Twitter using their recentSearch capability.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def recentSearch(twitterAccount: String): Action[AnyContent] = Action.async {
    twitterSearchService.recentSearch(twitterAccount).map { response =>
      Ok(Json.toJson(response))
    }
  }
}
