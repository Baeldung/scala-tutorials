package com.baeldung.scala.akka.http

import akka.actor.ActorSystem
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.Http
import scala.concurrent.ExecutionContext.Implicits.global
import scala._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import scala.util._
import akka.http.scaladsl.model.headers.RawHeader

object MovieServer extends App {

  implicit val system = ActorSystem("MoviesServer")
  val movieService = new MovieService()
  implicit val movieFormat = jsonFormat3(Movie)

  val route = path("movies" / "heartbeat") {
    get {
      complete("Success")
    }
  } ~ path("movies" / "test") {
    get {
      complete("Verified")
    }
  } ~ path("movies-sync") {
    complete(movieService.getAllMoviesSync())
  } ~ path("movies") {
    get {
      onComplete(movieService.getAllMovies()) {
        case util.Success(res) => complete(res)
        case util.Failure(ex)  => complete(StatusCodes.InternalServerError)
      }
    } ~ post {
      entity(as[Movie]) { movie =>
        onComplete(movieService.saveMovie(movie)) {
          case Success(res) => complete(res)
          case Failure(ex)  => complete(StatusCodes.InternalServerError)
        }
      }
    }
  } ~ path("movies" / IntNumber) { id =>
    put {
      entity(as[Movie]) { movie =>
        onComplete(movieService.updateMovie(id, movie)) {
          case Success(res) => complete(res)
          case Failure(ex)  => complete(StatusCodes.InternalServerError)
        }
      }
    }
  } ~ path("movies" / IntNumber) { id =>
    delete {
      onComplete(movieService.deleteMovie(id)) {
        case Success(res) => complete(res)
        case Failure(ex)  => complete(StatusCodes.InternalServerError)
      }
    }
  } ~ path("test-headers") {
    get {
      optionalHeaderValueByName("apiKey") { apiKey =>
        respondWithHeader(RawHeader("authenticated", "done")) {
          complete(s"Received apiKey in header as '$apiKey' ")
        }
      }
    }
  }

  val server = Http().newServerAt("localhost", 9090).bind(route)
  server.map { _ =>
    println("Successfully started on localhost:9090 ")
  } recover { case ex =>
    println("Failed to start the server due to: " + ex.getMessage)
  }
}
