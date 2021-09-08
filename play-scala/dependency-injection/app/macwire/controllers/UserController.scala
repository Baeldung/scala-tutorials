package macwire.controllers

import macwire.services.UserService
import play.api.libs.json.Json
import play.api.mvc._

class UserController(
  userService: UserService,
  val controllerComponents: ControllerComponents
) extends BaseController {

  def index() =
    Action { implicit request: Request[AnyContent] =>
      Ok(Json.toJson(userService.getAll()))
    }

}
