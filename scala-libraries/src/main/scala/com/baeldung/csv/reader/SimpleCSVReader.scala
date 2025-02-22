package com.baeldung.csv.reader

import java.io.{BufferedReader, InputStreamReader}
import scala.annotation.tailrec

class SimpleCSVReader extends CommaSeparatedValuesReader {

  override def read(fileName: String): CSVReadDigest = {
    val in = new InputStreamReader(getClass.getResourceAsStream(s"/$fileName"))
    val bufferedReader = new BufferedReader(in)

    @tailrec
    def readLinesRecursively(
      currentBufferedReader: BufferedReader,
      result: Seq[Seq[String]]
    ): Seq[Seq[String]] = {
      currentBufferedReader.readLine() match {
        case null => result
        case line =>
          readLinesRecursively(
            currentBufferedReader,
            result :+ line.split(",").toSeq
          )
      }
    }

    val csvLines = readLinesRecursively(bufferedReader, List())

    bufferedReader.close()

    CSVReadDigest(
      csvLines.head,
      csvLines.tail
    )
  }

}
