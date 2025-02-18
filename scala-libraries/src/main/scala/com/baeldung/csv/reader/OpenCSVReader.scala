package com.baeldung.csv.reader

import com.opencsv.CSVReader

import java.io.{File, FileInputStream, InputStreamReader}
import scala.annotation.tailrec

class OpenCSVReader extends CommaSeparatedValuesReader {

  override def read(file: File): CSVReadDigest = {

    val reader = new CSVReader(
      new InputStreamReader(new FileInputStream(file))
    )

    @tailrec
    def readLinesRecursively(
      currentReader: CSVReader,
      result: Seq[Seq[String]]
    ): Seq[Seq[String]] = {
      currentReader.readNext() match {
        case null => result
        case line => readLinesRecursively(currentReader, result :+ line.toSeq)
      }
    }

    val csvLines = readLinesRecursively(reader, List())
    reader.close()

    CSVReadDigest(
      csvLines.head,
      csvLines.tail
    )
  }
}
