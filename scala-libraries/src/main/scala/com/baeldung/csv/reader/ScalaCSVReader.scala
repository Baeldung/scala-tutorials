package com.baeldung.csv.reader

import com.github.tototoshi.csv.CSVReader

import java.io.InputStreamReader

class ScalaCSVReader extends CommaSeparatedValuesReader {

  override def read(fileName: String): CSVReadDigest = {
    val reader = CSVReader.open(
      new InputStreamReader(getClass.getResourceAsStream(s"/$fileName"))
    )
    val all = reader.all()
    reader.close()
    CSVReadDigest(all.head, all.tail)
  }
}
