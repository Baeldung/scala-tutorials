package scalatra.tutorial.auth

import org.scalatra.ScalatraBase
import org.scalatra.auth.{ScentryConfig, ScentrySupport}
import org.scalatra.auth.strategy.BasicAuthSupport
import scalatra.tutorial.model.User

import scala.util.Random

trait AuthSupport extends ScentrySupport[User] with BasicAuthSupport[User] {

  self: ScalatraBase =>

  override protected def fromSession: PartialFunction[String, User] = { email =>
    User(Random.nextLong(), email)
  }

  override protected def toSession: PartialFunction[User, String] = { user =>
    user.email
  }

  override protected def registerAuthStrategies(): Unit = {
    scentry.register("Basic", app => new CustomBasicAuthStrategy(app, realm))
  }

  override protected def configureScentry(): Unit = {
    scentry.unauthenticated {
      scentry.strategies("Basic").unauthenticated()
    }
  }

  protected val scentryConfig = new ScentryConfig {}

  override type ScentryConfiguration = ScentryConfig

  val realm = "Scalatra Basic Auth Example"

}
