package com.baeldung.scala.zio.resources
import zio._

object ResourceHandling extends ZIOAppDefault {

  val simpleZIO = ZIO.succeed {
    println("Creating Connection")
    "con"
  } 
  val finalizerBlock = ZIO.succeed(println("This is a finalizer!"))
  val zioWithFinalizer = simpleZIO.ensuring(finalizerBlock)

  val failingZIO = ZIO.fail {
    println("Some error occurred")
    -100
  }

  val complexZIO = simpleZIO *> failingZIO *> ZIO.succeed(println("Final step in chain"))
  val complexZIOWithFinalizer = complexZIO.ensuring(finalizerBlock)

  val finalizer2 = ZIO.suspend {
    throw new Exception
  }


  def acquireFile = ZIO.succeed(println("acquiring file")) *> ZIO.succeed("Sauron.txt")
  def releaseFile(file: String) = ZIO.succeed(println("Closing file: "+file))
  val fileContentZIO = ZIO.acquireReleaseWith(acquireFile)(releaseFile) { file =>
    ZIO.succeed(println("Reading the content from the file: "+file)) *>
    ZIO.succeed("One ring to rule them all!")
  }


  def acquireDB = ZIO.succeed(println("OPening DB Connection")) *> ZIO.succeed("pgsql://localhost:5432")
  def releaseDBCon(con: String) = ZIO.succeed(println("Closing DB Connection to URL: "+con))
  val nestedResourceZIO = ZIO.acquireReleaseWith(acquireFile)(releaseFile) { file =>
    ZIO.acquireReleaseWith(acquireDB)(releaseDBCon) { con =>
      ZIO.succeed(println("Reading the content from the file: "+file + "Writing to DB: "+con)) *>
      ZIO.succeed("One ring to rule them all AND bring them back!")
    }
  }

  val acquiredResource = ZIO.acquireRelease(acquireFile)(releaseFile)
  val acquireReleaseContent = for {
    file <- acquiredResource
    content <- ZIO.succeed(println("Reading from the acquired file: "+file)) *> ZIO.succeed("One ring to rule them all!")
  } yield content
  
  def acquireFileWithFailure = ZIO.succeed(println("acquiring file")) *> ZIO.succeed("Sauron.txt") *> ZIO.fail("ERROR")
  def fileContentFailedAcquire = ZIO.acquireReleaseWith(acquireFileWithFailure)(releaseFile) { file =>
    ZIO.succeed(println("reading from file"))
  }

  override def run = nestedResourceZIO
}