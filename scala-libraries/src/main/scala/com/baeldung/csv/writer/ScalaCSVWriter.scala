package com.baeldung.csv.writer

import com.github.tototoshi.csv.CSVWriter

import java.io.File
import scala.util.Try

class ScalaCSVWriter extends CommaSeparatedValuesWriter {

  override def write(
    file: File,
    headers: Seq[String],
    rows: Seq[Seq[String]]
  ): Try[Unit] = Try {
    val writer = CSVWriter.open(file)
    writer.writeRow(headers)
    writer.writeAll(rows)
    writer.close()
  }
}
