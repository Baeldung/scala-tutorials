package com.baeldung.csv.writer
import org.apache.commons.csv.{CSVFormat, CSVPrinter}

import java.io.FileWriter
import scala.util.Try

class ApacheCommonsCSVWriter extends CommaSeparatedValuesWriter {

  override def write(
    fileName: String,
    headers: Seq[String],
    rows: Seq[Seq[String]]
  ): Try[Unit] = Try {

    val csvFormat = CSVFormat.DEFAULT
      .builder()
      .setHeader(headers: _*)
      .build()

    val out = new FileWriter(s"apache-commons-$fileName")
    val printer = new CSVPrinter(out, csvFormat)
    rows.foreach(row => printer.printRecord(row: _*))
    printer.close()
  }
}
