package controllers

import javax.inject._
import play.api.mvc._

@Singleton class ArticleController @Inject() (
  val controllerComponents: ControllerComponents
) extends BaseController {

  def index(): Action[AnyContent] = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.index())
  }

  def greet(name: String): Action[AnyContent] = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.greet(name))
  }
}
