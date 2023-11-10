package guice.filter

import org.apache.pekko.stream.Materializer
import play.api.Logger
import play.api.mvc.{Filter, RequestHeader, Result}
import play.api.routing.{HandlerDef, Router}

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class LoggingFilter @Inject() (implicit
  val mat: Materializer,
  ec: ExecutionContext
) extends Filter {

  private val logger = Logger("time")

  def apply(
    nextFilter: RequestHeader => Future[Result]
  )(requestHeader: RequestHeader): Future[Result] = {

    val startTime = System.currentTimeMillis

    logger.info(s"called ${requestHeader.uri}")

    nextFilter(requestHeader).map { result =>
      val handlerDefOpt: Option[HandlerDef] =
        requestHeader.attrs.get(Router.Attrs.HandlerDef)
      val action = handlerDefOpt
        .map(handlerDef => s"${handlerDef.controller}.${handlerDef.method}")
        .getOrElse(requestHeader.uri)
      val endTime = System.currentTimeMillis
      val requestTime = endTime - startTime

      logger.info(
        s"${action} took ${requestTime}ms and returned ${result.header.status}"
      )

      result.withHeaders("Request-Time" -> requestTime.toString)
    }
  }
}
