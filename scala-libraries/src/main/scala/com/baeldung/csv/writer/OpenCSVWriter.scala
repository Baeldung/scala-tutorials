package com.baeldung.csv.writer

import com.opencsv.CSVWriter

import java.io.{BufferedWriter, File, FileWriter}
import scala.jdk.CollectionConverters.IterableHasAsJava
import scala.util.Try

class OpenCSVWriter extends CommaSeparatedValuesWriter {

  override def write(
    file: File,
    headers: Seq[String],
    rows: Seq[Seq[String]]
  ): Try[Unit] = Try(
    new CSVWriter(new BufferedWriter(new FileWriter(file)))
  ).flatMap((csvWriter: CSVWriter) =>
    Try {
      csvWriter.writeAll(
        (headers +: rows).map(_.toArray).asJava,
        false
      )
      csvWriter.close()
    }
  )
}
