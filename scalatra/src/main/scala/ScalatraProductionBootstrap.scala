import com.mchange.v2.c3p0.ComboPooledDataSource
import org.scalatra.LifeCycle
import scalatra.tutorial.servlet.{DbServlet, MyScalatraServlet, UserServlet}
import slick.jdbc.H2Profile.api._

import javax.servlet.ServletContext

class ScalatraProductionBootstrap extends LifeCycle {

  val cpds = new ComboPooledDataSource

  override def init(context: ServletContext) {
    val db = Database.forDataSource(cpds, None)
    context.mount(new UserServlet(db), "/user/*")
    context.mount(new DbServlet(db), "/db/*")
    context.mount(new MyScalatraServlet, "/*")
    context.setInitParameter("org.scalatra.environment", "production")
  }
}
