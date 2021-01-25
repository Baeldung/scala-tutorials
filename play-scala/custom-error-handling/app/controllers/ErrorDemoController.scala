package controllers

import javax.inject._
import play.api.mvc.{BaseController, ControllerComponents, Action, AnyContent}

@Singleton
class ErrorDemoController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  def noError(): Action[AnyContent] = Action {
    Ok
  }

  def exception(): Action[AnyContent] = Action {
    throw new RuntimeException("Pretend that we have an application error.")
    Ok // I add this line just to make the types match
  }

  def internalError(): Action[AnyContent] = Action {
    InternalServerError
  }

  def notFound(): Action[AnyContent] = Action {
    NotFound
  }

  def badRequest(): Action[AnyContent] = Action {
    BadRequest
  }
}
