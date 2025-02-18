package com.baeldung.csv.writer

import java.io.{File, PrintWriter}
import scala.util.Try

class SimpleCSVWriter extends CommaSeparatedValuesWriter {

  override def write(
    file: File,
    headers: Seq[String],
    rows: Seq[Seq[String]]
  ): Try[Unit] = Try {
    val writer = new PrintWriter(file)
    writer.println(headers.mkString(","))
    rows.foreach(row => writer.println(row.mkString(",")))
    writer.close()
  }

}
