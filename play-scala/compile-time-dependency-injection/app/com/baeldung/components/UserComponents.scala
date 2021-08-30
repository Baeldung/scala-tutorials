package com.baeldung.components

import com.softwaremill.macwire.wire
import com.baeldung.controllers.UserController
import play.api.BuiltInComponentsFromContext
import play.api.mvc.ControllerComponents
import com.baeldung.services.UserService

trait UserComponents {

  self: BuiltInComponentsFromContext =>

  lazy val cc: ControllerComponents = this.controllerComponents

  lazy val userService = wire[UserService]
  lazy val userController = wire[UserController]

}
