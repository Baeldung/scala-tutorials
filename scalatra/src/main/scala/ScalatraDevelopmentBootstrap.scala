import com.mchange.v2.c3p0.ComboPooledDataSource
import slick.jdbc.H2Profile.api._
import org.scalatra._
import scalatra.tutorial.servlet.{
  BasicAuthServlet,
  DbServlet,
  MyScalatraServlet,
  UserServlet
}

import javax.servlet.ServletContext

class ScalatraDevelopmentBootstrap extends LifeCycle {

  val cpds = new ComboPooledDataSource

  override def init(context: ServletContext) {
    val db = Database.forDataSource(cpds, None)
    context.mount(new UserServlet(db), "/user/*")
    context.mount(new DbServlet(db), "/db/*")
    context.mount(new BasicAuthServlet, "/basic-auth/*")
    context.mount(new MyScalatraServlet, "/*")
    context.setInitParameter("org.scalatra.environment", "development")
  }

  override def destroy(context: ServletContext): Unit = {
    super.destroy(context)
    cpds.close()
  }
}
