package guice.controllers

import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._
import guice.services.UserService

class UserController @Inject() (
  userService: UserService,
  val controllerComponents: ControllerComponents
) extends BaseController {

  def index() =
    Action { implicit request: Request[AnyContent] =>
      Ok(Json.toJson(userService.getAll()))
    }

}
