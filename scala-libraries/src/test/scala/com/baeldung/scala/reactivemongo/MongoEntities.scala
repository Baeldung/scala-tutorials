package com.baeldung.scala.reactivemongo

import reactivemongo.api.bson.{BSONDocumentReader, BSONDocumentWriter, Macros}

case class Movie(name:String, leadActor:String, genre:String, durationInMin: Int)

object MongoEntityImplicits {
  implicit def moviesWriter: BSONDocumentWriter[Movie] = Macros.writer[Movie]
  implicit def moviesReader: BSONDocumentReader[Movie] = Macros.reader[Movie]
}
