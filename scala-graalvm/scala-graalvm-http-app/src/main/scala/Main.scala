import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import scala.concurrent.Await
import scala.util.{Failure, Success}
import scala.concurrent.duration._

object Main extends App {

  val host = "0.0.0.0"
  val port = 9000

  implicit val system: ActorSystem = ActorSystem(name = "baeldunghttpapp")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  import system.dispatcher

  import akka.http.scaladsl.server.Directives._

  def route = path("hello") {
    get {
      complete("Hello from Baeldung!")
    }
  }

  val binding = Http().bindAndHandle(route, host, port)
  binding.onComplete {
    case Success(_) =>
      println("ScalaGraalVM: Listening for incoming connections!")
    case Failure(error) => println(s"Failed: ${error.getMessage}")
  }
  scala.io.StdIn.readLine()
}
