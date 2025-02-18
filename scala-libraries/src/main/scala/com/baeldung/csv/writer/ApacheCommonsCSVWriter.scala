package com.baeldung.csv.writer
import org.apache.commons.csv.{CSVFormat, CSVPrinter}

import java.io.{File, FileWriter}
import scala.util.Try

class ApacheCommonsCSVWriter extends CommaSeparatedValuesWriter {

  override def write(
    file: File,
    headers: Seq[String],
    rows: Seq[Seq[String]]
  ): Try[Unit] = Try {

    val csvFormat = CSVFormat.DEFAULT
      .builder()
      .setHeader(headers: _*)
      .build()

    val out = new FileWriter(file)
    val printer = new CSVPrinter(out, csvFormat)
    rows.foreach(row => printer.printRecord(row: _*))
    printer.close()
  }
}
