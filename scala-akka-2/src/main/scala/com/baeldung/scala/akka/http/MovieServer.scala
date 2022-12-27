package com.baeldung.scala.akka.http

import akka.actor.ActorSystem
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.Http
import scala.concurrent.ExecutionContext.Implicits.global
import scala._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import scala.util.Success

object MovieServer extends App {

    implicit val system = ActorSystem("MoviesServer")
    val movieService = new MovieService()
    implicit val movieFormat = jsonFormat2(Movie)

    val route = path("movies" / "heartbeat") {
        get {
            complete("Success")
        }
    } ~ path("movies") {
        get {            
            onComplete(movieService.getAllMovies()){
                case util.Success(res) => complete(res)
                case util.Failure(ex) => complete(StatusCodes.InternalServerError)
            }
        }
    } ~ path("movies" / Segment / IntNumber) { (name, length) =>
        get {            
            onComplete(movieService.saveMovie(Movie(name,length))){
                case util.Success(res) => complete("Saved")
                case util.Failure(ex) => complete(StatusCodes.InternalServerError)
            }
        }
    }


    val server = Http().newServerAt("localhost", 9090).bind(route)
    server.foreach{ s=>
        println("Successfully started on localhost:9090 ")
    }
}