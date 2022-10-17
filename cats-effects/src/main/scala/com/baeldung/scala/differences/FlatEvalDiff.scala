package com.baeldung.scala.differences

import cats.effect.IOApp
import cats.effect.IO
import cats.effect.Resource
import cats.effect.std.Random

object FlatEvalDiff extends IOApp.Simple {

  val strIO = IO("This is a string IO")  
  
  def countLetters(str: String): IO[Int] = IO(str.length())

  val countIO: IO[Int] = strIO.flatMap(s => countLetters(s))

  def countLettersWithPrint(str: String): IO[Unit] = IO {
    println("String length is "+str.length())
  }
  val flatTapIO: IO[String] = strIO.flatTap(countLettersWithPrint)

  val flatTapIO_V2: IO[String] = strIO.map{r => 
    countLettersWithPrint(r)
    r
  }

  case class SimpleConnection(url: String)
  case class ComplexConnection(url: String)
  def acquireResource(): IO[SimpleConnection] = {
    IO.println("Opening Simple Connection") >> IO(SimpleConnection("localhost:8000")) 
  }
  def releaseResource(con: SimpleConnection): IO[Unit] = {
    IO.println("Closing Simple Connection: "+con.url)
  }
  
  val resource: Resource[IO,SimpleConnection] = Resource.make(acquireResource)(releaseResource)
  val simpleResourceData: IO[Unit] = resource.use(simpleConn => IO.println("Result using Simple Resource"))

  def transformConnection(con: SimpleConnection) = {
    IO {
      println("Transforming connection to complex")
      ComplexConnection(con.url)
    }
  }

  val modifiedResource: Resource[IO,ComplexConnection] = resource.evalMap(con => transformConnection(con))
  val tappedResource: Resource[IO,SimpleConnection] = resource.evalTap(con => transformConnection(con))

  val result: IO[Unit] = tappedResource.use(c => IO.println("Using complex connection to execute.."))


  override def run: IO[Unit] = result

}