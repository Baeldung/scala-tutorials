package com.baeldung.csv.reader

case class CSVReadDigest(headers: Seq[String], rows: Seq[Seq[String]])
trait CommaSeparatedValuesReader {

  def read(fileName: String): CSVReadDigest

}
