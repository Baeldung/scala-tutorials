package com.baeldung.arrival.controller.action

import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

trait SourceActions {

  def SourceActionFilter(implicit ec: ExecutionContext): ActionFilter[Request] =
    new ActionFilter[Request] {
      override protected def filter[A](
        request: Request[A]
      ): Future[Option[Result]] = {
        Future.successful {
          request.headers.get("source") match {
            case Some(_) => None
            case None    => Some(Results.BadRequest("Source header is absent"))
          }
        }
      }

      override protected def executionContext: ExecutionContext = ec
    }

  def SourceAction(
    anyContentParser: BodyParser[AnyContent]
  )(implicit ec: ExecutionContext): ActionBuilder[Request, AnyContent] =
    new ActionBuilderImpl[AnyContent](
      anyContentParser
    ) andThen SourceActionFilter

}
