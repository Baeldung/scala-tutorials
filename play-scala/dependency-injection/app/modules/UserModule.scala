package modules

import controllers.UserController
import com.google.inject.AbstractModule
import play.api.{Configuration, Environment}
import services.UserService

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
