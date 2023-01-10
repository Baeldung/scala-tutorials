package com.baeldung.scala.akka.http
import scala.concurrent.Future
import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global

case class Movie(id: Int, name: String, length: Int)

class MovieService {
  var movies: List[Movie] = List(Movie(100, "The Grand Budapest Hotel", 100))

  def getAllMovies(): Future[List[Movie]] = Future.successful(movies.toList)

  def getAllMoviesSync(): List[Movie] = movies.toList

  def saveMovie(movie: Movie): Future[Movie] = {
    movies = movies :+ movie
    Future.successful(movie)
  }

  def updateMovie(id: Int, movie: Movie): Future[Movie] = {
    Future {
      val updated = movies.filterNot(_.id == id) :+ movie
      movies = updated
      movie
    }
  }

  def getMovie(name: String): Future[Option[Movie]] =
    Future.successful(movies.find(_.name == name))

  def deleteMovie(id: Int): Future[String] = {
    val removed = movies.filterNot(_.id == id)
    movies = removed
    Future.successful("Movie deleted successfully")
  }
}
