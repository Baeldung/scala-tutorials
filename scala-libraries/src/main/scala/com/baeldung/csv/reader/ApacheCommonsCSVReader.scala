package com.baeldung.csv.reader

import org.apache.commons.csv.CSVFormat

import java.io.{File, FileInputStream, InputStreamReader}
import scala.jdk.CollectionConverters.IterableHasAsScala

class ApacheCommonsCSVReader extends CommaSeparatedValuesReader {

  override def read(file: File): CSVReadDigest = {
    val in = new InputStreamReader(new FileInputStream(file))
    val csvParser = CSVFormat.DEFAULT
      .builder()
      .setHeader()
      .build()
      .parse(in)
    val result = CSVReadDigest(
      csvParser.getHeaderNames.asScala.toSeq,
      csvParser.getRecords.asScala.map(r => r.values().toSeq).toSeq
    )
    csvParser.close()
    result
  }
}
