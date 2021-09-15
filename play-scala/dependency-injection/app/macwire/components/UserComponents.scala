package macwire.components

import com.softwaremill.macwire.wire
import macwire.controllers.UserController
import macwire.services.UserService
import play.api.BuiltInComponentsFromContext
import play.api.mvc.ControllerComponents

trait UserComponents {

  self: BuiltInComponentsFromContext =>

  lazy val cc: ControllerComponents = this.controllerComponents

  lazy val userService = wire[UserService]
  lazy val userController = wire[UserController]

}
