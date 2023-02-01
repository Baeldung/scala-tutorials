package com.baeldung.scala.fs2

import cats.effect.testing.scalatest.AsyncIOSpec
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.io.Source

class FS2SampleTest extends AsyncFlatSpec with AsyncIOSpec with Matchers {

  it should "read from a file and calculate word count" in {
    val outFile = "wc-fs2-output.log"
    val io = Fs2Examples.readAndWriteFile("fs2data.txt", outFile).compile.drain
    val res = io.map { _ =>
      Source.fromFile(outFile).getLines().toList
    }
    res.asserting(_.nonEmpty shouldBe true)
  }

}