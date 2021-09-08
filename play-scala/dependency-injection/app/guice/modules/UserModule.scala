package guice.modules

import com.google.inject.AbstractModule
import guice.controllers.UserController
import play.api.{Configuration, Environment}
import guice.services.UserService

import javax.inject.Singleton

class UserModule(environment: Environment, configuration: Configuration)
  extends AbstractModule {

  override def configure(): Unit = {

    bind(classOf[UserService])
      .in(classOf[Singleton])

    bind(classOf[UserController])
      .in(classOf[Singleton])

  }

}
