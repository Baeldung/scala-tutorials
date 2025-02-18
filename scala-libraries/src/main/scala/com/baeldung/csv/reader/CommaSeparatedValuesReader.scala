package com.baeldung.csv.reader

import java.io.File

case class CSVReadDigest(headers: Seq[String], rows: Seq[Seq[String]])
trait CommaSeparatedValuesReader {

  def read(file: File): CSVReadDigest

}
