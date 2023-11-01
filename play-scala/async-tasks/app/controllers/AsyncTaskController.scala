package controllers

import akka.actor.{ActorRef, ActorSystem}

import javax.inject._
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import java.time.LocalDateTime
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.language.postfixOps

@Singleton
class AsyncTaskController @Inject() (
  val controllerComponents: ControllerComponents,
  val actorSystem: ActorSystem,
  @Named("async-job-actor") actor: ActorRef
)(implicit ec: ExecutionContext)
  extends BaseController {
  def runAsync(): Action[AnyContent] = Action {
    Console.println(s"In route handler: ${LocalDateTime.now()}")
    actorSystem.scheduler.scheduleOnce(30 seconds) {
      Console.println(s"30 seconds later: ${LocalDateTime.now()}")
    }
    actor ! "YELLING AT ACTOR"
    actorSystem.scheduler.scheduleOnce(
      10 seconds,
      actor,
      "A TEST MESSAGE!!"
    )

    val cancellable = actorSystem.scheduler.scheduleAtFixedRate(
      10 seconds,
      5 minutes,
      actor,
      "recurring task"
    )

    cancellable.cancel()
    Ok
  }
}
