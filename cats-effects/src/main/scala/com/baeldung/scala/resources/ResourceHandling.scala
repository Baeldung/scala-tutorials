package com.baeldung.scala.resources

import cats.effect.IO
import java.io.File
import scala.io.Source
import cats.effect.IOApp
import scala.io.BufferedSource
import java.io.FileWriter
import cats.effect.Resource

object ResourceHandling extends IOApp.Simple {

  def sourceIO: IO[Source] =
    IO.println("Acquiring source to read file") >> IO(
      Source.fromResource("sample.txt")
    )
  def readLines(source: Source): IO[String] =
    IO.println("Reading contents from the file") >> IO(
      source.getLines().mkString("\n")
    )
  def closeFile(source: Source): IO[Unit] =
    IO.println("Closing the file after read") >> IO(source.close())

  def writerIO: IO[FileWriter] =
    IO.println("Acquiring file to write") >> IO(
      new FileWriter("sample_write.txt")
    )

  def writeLines(writer: FileWriter, content: String): IO[Unit] =
    IO.println("Writing the contents to file") >> IO(writer.write(content))

  def closeWriteFile(writer: FileWriter): IO[Unit] =
    IO.println("Closing the file writer") >> IO(writer.close())

  val bracketRead: IO[String] =
    sourceIO.bracket(src => readLines(src))(src => closeFile(src))

  val bracketReadWrite = sourceIO.bracket { src =>
    val contentsIO = readLines(src)
    writerIO.bracket(fw =>
      contentsIO.flatMap(contents => writeLines(fw, contents))
    )(fw => closeWriteFile(fw))
  } { src =>
    closeFile(src)
  }

  val makeResourceForRead: Resource[IO, Source] =
    Resource.make(sourceIO)(src => closeFile(src))
  val readWithResource: IO[String] =
    makeResourceForRead.use(src => readLines(src))

  val makeResourceForWrite: Resource[IO, FileWriter] =
    Resource.make(writerIO)(fw => closeWriteFile(fw))

  val readWriteWithResource: IO[Unit] = for {
    content <- readWithResource
    _ <- makeResourceForWrite.use(fw => writeLines(fw, content))
  } yield ()

  override def run: IO[Unit] = readWriteWithResource

}
