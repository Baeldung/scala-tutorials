package com.baeldung.scala.akka.http
import scala.concurrent.Future
import scala.collection.mutable.ListBuffer

case class Movie(name:String, length: Int)

class MovieService {
    val movies = ListBuffer.empty[Movie]

    def getAllMovies(): Future[List[Movie]] = Future.successful(movies.toList)

    def saveMovie(movie: Movie): Future[Boolean] = {
        movies.append(movie)
        Future.successful(true)
    }

    def getMovie(name:String): Future[Option[Movie]] = Future.successful(movies.find(_.name == name))

    def deleteMovie(name: String): Future[Boolean] = {
        val movie = movies.find(_.name == name)
        if(movie.isDefined) {
            movies -= movie.get
            Future.successful(true)
        } else {
            Future.failed(new IllegalArgumentException("There is no movie with name: "+name))
        }
    }
}