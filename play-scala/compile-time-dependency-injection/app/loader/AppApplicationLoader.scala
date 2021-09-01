package loader

import com.softwaremill.macwire._
import components.{OrderComponents, UserComponents}
import play.api.ApplicationLoader.Context
import play.api.routing.Router
import play.api.{ApplicationLoader, BuiltInComponents, BuiltInComponentsFromContext}
import play.filters.HttpFiltersComponents
import router.Routes

class AppApplicationLoader extends ApplicationLoader {
  def load(context: Context) = {

    new AppComponents(context).application
  }
}

class AppComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
  with BuiltInComponents
  with HttpFiltersComponents
  with UserComponents
  with OrderComponents {

  lazy val router: Router = {
    lazy val prefix = "/"
    wire[Routes]
  }
}
