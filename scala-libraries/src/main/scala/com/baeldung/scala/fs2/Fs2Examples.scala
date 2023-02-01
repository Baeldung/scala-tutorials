package com.baeldung.scala.fs2

import cats.effect.{Async, ExitCode, IO, IOApp}
import fs2._
import fs2.io.file.{Files, Path}

import java.nio.file.Paths

object Fs2Examples {

  /** OOM from processing large file
    */

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

  /** Using Fs2 Streams
    */

  // defining a simple stresm of Ints
  val intStream: Stream[Pure, Int] = Stream(1, 2, 3, 4, 5)

  // defining a pipe
  val add1Pipe: Pipe[Pure, Int, Int] = _.map(_ + 1)

  /** Word count Using Fs2
    */

  def readAndWriteFile(readFrom: String, writeTo: String): Stream[IO, Unit] = {

    val path = ClassLoader.getSystemResource(readFrom)
    val fs2Path = Path.fromNioPath(java.nio.file.Path.of(path.toURI))

    val source: Stream[IO, Byte] =
      Files[IO].readAll(fs2Path)

    val pipe: Pipe[IO, Byte, Byte] = src =>
      src
        .through(text.utf8.decode)
        .through(text.lines)
        .flatMap(line => Stream.apply(line.split("\\W+"): _*))
        .fold(Map.empty[String, Int]) { (count, word) =>
          count + (word -> (count.getOrElse(word, 0) + 1))
        }
        .map(_.foldLeft("") { case (accumulator, (word, count)) =>
          accumulator + s"$word = $count\n"
        })
        .through(text.utf8.encode)

    val sink: Pipe[IO, Byte, Unit] =
      Files[IO].writeAll(Path(writeTo))

    val stream: Stream[IO, Unit] =
      source
        .through(pipe)
        .through(sink)

    stream
  }

  // Batching in Fs2
  Stream((1 to 100): _*)
    .chunkN(10) // group 10 elements together
    .map(println)
    .compile
    .drain

  // Asynchronicity in fs2
  def writeToSocket[F[_]: Async](chunk: Chunk[String]): F[Unit] = {
    Async[F].async_ { callback =>
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
