package com.baeldung.csv.writer

import java.io.File
import scala.util.Try

trait CommaSeparatedValuesWriter {

  def write(
    file: File,
    headers: Seq[String],
    rows: Seq[Seq[String]]
  ): Try[Unit]

}
