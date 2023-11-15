import akka.NotUsed
import akka.stream.{IOResult, Materializer}
import akka.stream.scaladsl.{FileIO, Framing, Source}
import akka.util.ByteString
import akka.stream.alpakka.csv.scaladsl.{CsvParsing, CsvToMap}

import java.nio.file.Paths
import scala.concurrent.Future

object ReadingCSV {

  def read(
    path: String
  )(implicit mat: Materializer): Source[String, Future[IOResult]] = {
    FileIO
      .fromPath(Paths.get(path))
      .via(Framing.delimiter(ByteString("\n"), 256, true).map(_.utf8String))
  }

  def readFromString(
    csvString: String
  )(implicit mat: Materializer): Source[String, NotUsed] = {
    Source
      .single(ByteString(csvString))
      .via(Framing.delimiter(ByteString("\n"), 256, true).map(_.utf8String))
  }

  def readWithAlpakka(
    path: String
  )(implicit mat: Materializer): Source[List[String], Future[IOResult]] = {
    FileIO
      .fromPath(Paths.get(path))
      .via(CsvParsing.lineScanner())
      .map(_.map(_.utf8String))
  }

  def readWithAlpakkaAsMap(path: String)(implicit
    mat: Materializer
  ): Source[Map[String, String], Future[IOResult]] = {
    FileIO
      .fromPath(Paths.get(path))
      .via(CsvParsing.lineScanner())
      .via(CsvToMap.toMapAsStrings())
  }
}
