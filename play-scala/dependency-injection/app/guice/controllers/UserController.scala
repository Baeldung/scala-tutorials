package guice.controllers

import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._
import guice.services.UserService
import play.api.Logger

class UserController @Inject() (
  userService: UserService,
  val controllerComponents: ControllerComponents
) extends BaseController {

  private val logger = Logger(getClass)

  def index() =
    Action { implicit request: Request[AnyContent] =>
      logger.info("get all users called")
      Ok(Json.toJson(userService.getAll()))
    }

}
