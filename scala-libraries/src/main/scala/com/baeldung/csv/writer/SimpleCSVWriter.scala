package com.baeldung.csv.writer

import java.io.PrintWriter
import scala.util.Try

class SimpleCSVWriter extends CommaSeparatedValuesWriter {

  override def write(
    fileName: String,
    headers: Seq[String],
    rows: Seq[Seq[String]]
  ): Try[Unit] = Try {
    val writer = new PrintWriter(s"simple-$fileName")
    writer.println(headers.mkString(","))
    rows.foreach(row => writer.println(row.mkString(",")))
    writer.close()
  }

}
