package com.baeldung.scala

import java.io.{File, IOException}
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.{FileVisitResult, Files, Path, SimpleFileVisitor}
import scala.reflect.io.Directory

object FileDeletion {

  def deletePureScalaDeletion(path: String): Unit = {
    new Directory(new File(path))
      .deleteRecursively()
  }

  def deleteRecursively(file: File): Unit = {
    if (file.isDirectory) {
      file.listFiles.foreach(deleteRecursively)
    }
    if (file.exists && !file.delete) {
      throw new Exception(s"Unable to delete ${file.getAbsolutePath}")
    }
  }

  def deleteNio(root: Path): Unit = {
    Files.walkFileTree(
      root,
      new SimpleFileVisitor[Path] {
        override def visitFile(
          file: Path,
          attrs: BasicFileAttributes
        ): FileVisitResult = {
          Files.delete(file)
          FileVisitResult.CONTINUE
        }
        override def postVisitDirectory(
          dir: Path,
          exc: IOException
        ): FileVisitResult = {
          Files.delete(dir)
          FileVisitResult.CONTINUE
        }
      }
    )
  }
}
