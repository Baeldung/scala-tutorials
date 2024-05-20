package scalatra.tutorial.model

import org.scalatra.FutureSupport
import slick.jdbc.H2Profile.api._

trait DbSupport extends FutureSupport {

  protected implicit def executor: scala.concurrent.ExecutionContext =
    scala.concurrent.ExecutionContext.Implicits.global

  def db: Database

}
