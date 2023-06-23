package controllers

import javax.inject._
import play.api._
import play.api.mvc._

@Singleton class HomeController @Inject() (
  val controllerComponents: ControllerComponents
) extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.firstexample())
  }

  def printSum(first: Long, second: Long) = Action {
    implicit request: Request[AnyContent] =>
      val sum = first + second
      Ok(views.html.index(sum))
  }
}
