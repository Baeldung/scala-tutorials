package com.baeldung.scala.fs2

import java.io.{File, PrintWriter}
import java.nio.file.Paths

import cats.effect.{Async, Blocker, ContextShift, IO}

import scala.util.Random
import fs2._

import scala.collection.mutable

object Fs2Examples {

  /**
   * OOM from processing large file
   * */

//  def readAndWriteFile(readFrom: String, writeTo: String) = {
//    val counts = mutable.Map.empty[String, Int]
//    val fileSource = scala.io.Source.fromFile(readFrom)
//    try {
//      fileSource
//        .getLines()
//        .toList
//        .flatMap(_.split("\\W+"))
//        .foreach { word =>
//          counts += (word -> (counts.getOrElse(word, 0) + 1))
//        }
//    } finally {
//      fileSource.close()
//    }
//    val fileContent = counts.foldLeft("") {
//      case (accumulator, (word, count)) =>
//        accumulator + s"$word = $count\n"
//    }
//
//    val writer = new PrintWriter(new File(writeTo))
//
//    writer.write(fileContent)
//    writer.close()
//  }


  /**
   * Using Fs2 Streams
   * */

  //defining a simple stresm of Ints
  val intStream: Stream[Pure, Int] = Stream(1, 2, 3, 4, 5)

  //defining a pipe
  val add1Pipe: Pipe[Pure, Int, Int] = _.map(_ + 1)


  /**
   * Word count Using Fs2
   * */

  implicit val ioContextShift: ContextShift[IO] =
    IO.contextShift(scala.concurrent.ExecutionContext.Implicits.global)

  def readAndWriteFile(readFrom: String, writeTo: String): Stream[IO, Unit] =
    Stream.resource(Blocker[IO]).flatMap { blocker =>
      val source: Stream[IO, Byte] =
        io.file.readAll[IO](Paths.get(readFrom), blocker, 4096)

      val pipe: Pipe[IO, Byte, Byte] = src =>
        src
          .through(text.utf8Decode)
          .through(text.lines)
          .flatMap(line => Stream.apply(line.split("\\W+"): _*))
          .fold(Map.empty[String, Int]) { (count, word) =>
            count + (word -> (count.getOrElse(word, 0) + 1))
          }
          .map(_.foldLeft("") {
            case (accumulator, (word, count)) =>
              accumulator + s"$word = $count\n"
          })
          .through(text.utf8Encode)

      val sink: Pipe[IO, Byte, Unit] =
        io.file.writeAll(Paths.get(writeTo), blocker)

      val stream: Stream[IO, Unit] =
        source
          .through(pipe)
          .through(sink)

      stream
    }


  // Batching in Fs2
  Stream((1 to 100) : _*)
    .chunkN(10) // group 10 elements together
    .map(println)
    .compile
    .drain


  // Asynchronicity in fs2
  def writeToSocket[F[_]: Async](chunk: Chunk[String]): F[Unit] = {
    Async[F].async { callback =>
      println(
        s"[thread: ${Thread.currentThread().getName}] :: Writing $chunk to socket"
      )
      callback(Right(()))
    }

  }

  Stream((1 to 100).map(_.toString): _*)
    .chunkN(10)
    .covary[IO]
    .parEvalMapUnordered(10)(writeToSocket[IO])
    .compile
    .drain

}
