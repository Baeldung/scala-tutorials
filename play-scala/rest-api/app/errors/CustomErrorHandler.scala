package errors

import controllers.routes
import play.api.Logging
import play.api.http.HttpErrorHandler
import play.api.http.Status._
import play.api.mvc.Results._
import play.api.mvc._

import javax.inject.Singleton
import scala.concurrent._

@Singleton
class CustomErrorHandler extends HttpErrorHandler with Logging {
  def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] = {
    if (statusCode == NOT_FOUND) { // works only when we access a not existing page, not when we return NotFound on purpose
      logger.info(s"Redirect(routes.TodoListController.getAll())")
      Future.successful(Redirect(routes.TodoListController.getAll()))
    } else {
      logger.info(s"A client error occurred.")
      Future.successful(
        Status(statusCode)("A client error occurred.")
      )
    }
  }

  def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
    Future.successful(
      InternalServerError("A server error occurred: " + exception.getMessage)
    )
  }
}