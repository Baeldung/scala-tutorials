package com.baeldung.scala.akka.discovery

import akka.actor.typed.receptionist.Receptionist.{Find, Listing}
import akka.actor.typed.receptionist.{Receptionist, ServiceKey}
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorSystem, Behavior}
import akka.util.Timeout

import scala.concurrent.duration.DurationInt
import scala.util.{Failure, Success}

object Main extends App {

  object Worker {
    sealed trait WorkerMessage

    object WorkerMessage {
      // simple message for workers to identify themselves
      case object IdentifyYourself extends WorkerMessage

    }

    // key to uniquely identify Worker actors
    val key: ServiceKey[WorkerMessage] = ServiceKey("Worker")


    import WorkerMessage._

    def apply(id: Int): Behavior[WorkerMessage] = Behaviors.setup { context =>


      // register actor with receptionist using the key and passing itself
      context.system.receptionist ! Receptionist.Register(key, context.self)

      Behaviors.receiveMessage {
        case IdentifyYourself =>
          println(s"Hello, I am worker $id")
          Behaviors.same
      }
    }
  }


  object Master {
    sealed trait MasterMessage

    object MasterMessage {
      case class StartWorkers(numWorker: Int) extends MasterMessage
      case class IdentifyWorker(id: Int) extends MasterMessage
      case object Done extends MasterMessage
      case object Failed extends MasterMessage
    }

    import MasterMessage._

    def workerName(id: Int) = {
      s"Worker-$id"
    }

    def apply(): Behavior[MasterMessage] = Behaviors.setup { context =>

      Behaviors.receiveMessage {
        case StartWorkers(numWorker) =>

          // spin up new workers
          for (id <- 0 to numWorker) {
            context.spawn(Worker(id), workerName(id))
          }
          Behaviors.same


        case IdentifyWorker(id) =>
          implicit val timeout: Timeout = 1.second
          context.ask(
            context.system.receptionist,
            Find(Worker.key) // ask the receptionist for actors with the key defined by Worker.key
          ) {
            case Success(listing: Listing) =>
              val instances = listing.serviceInstances(Worker.key)

              // find worker with the correct id
              val maybeWorker = instances.find { worker =>
                worker.path.name contentEquals workerName(id)
              }
              maybeWorker match {
                case Some(worker) =>
                  worker ! Worker.WorkerMessage.IdentifyYourself
                case None =>
                  println("worker not found ): ")
              }
              MasterMessage.Done

            case Failure(_) =>
              MasterMessage.Failed
          }

          Behaviors.same
      }
    }
  }


  import Master.MasterMessage

  // create the ActorSystem
  val master: ActorSystem[MasterMessage] = ActorSystem(
    Master(),
    "master"
  )

  // send the Start message to the Master Actor
  master ! MasterMessage.StartWorkers(10)



  // prints "Hello, I am worker 5"
  master ! MasterMessage.IdentifyWorker(5)

}

