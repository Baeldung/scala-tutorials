package com.baeldung.csv.writer

import scala.util.Try

trait CommaSeparatedValuesWriter {

  def write(
    fileName: String,
    headers: Seq[String],
    rows: Seq[Seq[String]]
  ): Try[Unit]

}
