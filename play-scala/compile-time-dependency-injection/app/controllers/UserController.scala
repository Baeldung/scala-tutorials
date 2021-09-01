package controllers

import play.api.libs.json.Json
import play.api.mvc._
import services.UserService

import javax.inject._

class UserController @Inject() (
  userService: UserService,
  val controllerComponents: ControllerComponents
) extends BaseController {

  def index() =
    Action { implicit request: Request[AnyContent] =>
      Ok(Json.toJson(userService.getAll()))
    }

}
