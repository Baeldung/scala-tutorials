package com.baeldung.scala.fs2

import cats.effect.testing.scalatest.AsyncIOSpec
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

import java.nio.file.Paths
import scala.io.Source

class FS2SampleUnitTest extends AsyncFlatSpec with AsyncIOSpec with Matchers {

  it should "read from a file and calculate word count" in {
    val outFile = "wc-fs2-output.log"
    val loc = "scala-libraries-fp/src/test/resources/fs2data.txt"
    val io = Fs2Examples.readAndWriteFile(loc, outFile).compile.drain
    val res = io.map { _ =>
      Source.fromFile(outFile).getLines().toList
    }
    res.asserting(_.nonEmpty shouldBe true)
  }

}
