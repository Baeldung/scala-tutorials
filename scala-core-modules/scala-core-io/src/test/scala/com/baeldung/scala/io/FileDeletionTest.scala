package com.baeldung.scala.io

import com.baeldung.scala.FileDeletion
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.BeforeAndAfterAll

import java.io.{File, FileWriter}
import java.nio.file.{Files, Paths}

class FileDeletionTest
  extends AnyFlatSpec
  with Matchers
  with BeforeAndAfterAll {
  val path = "tmp"
  override def beforeAll(): Unit = {
    val subDir = "otherDir"
    Files.createDirectories(Paths.get(path))
    Files.createDirectories(Paths.get(path + "/" + subDir))
    def createFile(path: String): Unit =
      new FileWriter(new File(path)) {
        try { write("content") }
        finally { close() }
      }

    createFile(s"$path/data1")
    createFile(s"$path/data2")
    createFile(s"$path/$subDir/data3")
    val dir = new File(path)
    assert(dir.isDirectory && dir.listFiles().length == 3)
  }

  "deletion with nio" should "delete all directory" in {
    FileDeletion.deleteNio(Paths.get(path))
    assert(!new File(path).exists())
  }

  "delete recursively" should "delete all directory" in {
    FileDeletion.deleteRecursively(new File(path))
    assert(!new File(path).exists())
  }
}
