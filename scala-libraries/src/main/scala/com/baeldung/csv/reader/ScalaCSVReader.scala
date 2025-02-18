package com.baeldung.csv.reader

import com.github.tototoshi.csv.CSVReader

import java.io.File

class ScalaCSVReader extends CommaSeparatedValuesReader {

  override def read(file: File): CSVReadDigest = {
    val reader = CSVReader.open(file)
    val all = reader.all()
    reader.close()
    CSVReadDigest(all.head, all.tail)
  }
}
