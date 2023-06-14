package errors

import controllers.routes
import play.api.http.HttpErrorHandler
import play.api.mvc._
import play.api.http.Status._
import play.api.mvc.Results._
import scala.concurrent._
import javax.inject.Singleton

@Singleton
class CustomErrorHandler extends HttpErrorHandler {
  def onClientError(
    request: RequestHeader,
    statusCode: Int,
    message: String
  ): Future[Result] = {
    if (statusCode == NOT_FOUND) { // works only when we access a not existing page, not when we return NotFound on purpose
      Future.successful(Redirect(routes.ErrorDemoController.noError()))
    } else {
      Future.successful(
        Status(statusCode)("A client error occurred.")
      )
    }
  }

  def onServerError(
    request: RequestHeader,
    exception: Throwable
  ): Future[Result] = {
    Future.successful(
      InternalServerError("A server error occurred: " + exception.getMessage)
    )
  }
}
