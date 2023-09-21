package com.baeldung.circeYaml

import io.circe.yaml
import io.circe.*
import cats.syntax.either.*
import io.circe.generic.auto.*
import java.io.FileReader
import java.io.File
import scala.util.Try
import io.circe.parser.*
import java.io.FileWriter
import io.circe.yaml.syntax.*

object YamlExample:
  case class Server(host: String, port: Int)
  case class OrdersConfig(
    name: String,
    server: Server,
    serverType: List[String]
  )

  val ordersYamlConfig: String =
    """
      name: Orders String
      server:
          host: localhost
          port: 8080
      serverType: 
          - Http
          - Grpc
    """
  val ordersStringConfig: Either[ParsingFailure, Json] =
    yaml.parser.parse(ordersYamlConfig)

  def processJson(
    json: Either[ParsingFailure, Json]
  ): Either[Error, OrdersConfig] =
    json
      .leftMap(err => err: Error)
      .flatMap(_.as[OrdersConfig])

  def printValue(value: Either[Error | Throwable, OrdersConfig]) =
    value match
      case Right(v)  => println(v)
      case Left(err) => println(err.getMessage)

  // Reading a yaml file
  val yamlFileReader: Either[Throwable, FileReader] =
    Try {
      new FileReader(
        "src/main/scala/resources/orders.yaml"
      )
    }.toEither

  val ordersFileConfig: Either[Throwable, OrdersConfig] =
    yamlFileReader
      .map(fileReader => processJson(yaml.parser.parse(fileReader)))
      .flatten

  // Reading multiple yaml documents in a single file.
  val yamlFileReader2: Either[Throwable, FileReader] =
    Try {
      new FileReader(
        "src/main/scala/resources/service.yaml"
      )
    }.toEither

  val ordersFileConfig2: Either[Throwable, List[Either[Error, OrdersConfig]]] =
    yamlFileReader2
      .map(fileReader => yaml.parser.parseDocuments(fileReader).toList)
      .map(_.map(processJson))

  val jsonString =
    """
      {
        "name": "Orders Json",
        "server":
          {
            "host": "localhost",
            "port": 8080
          },
        "serverType": ["Http", "Grpc"]
      }
    """

  val jsonConfig: Either[ParsingFailure, Json] = parse(jsonString)

  val fileWriter: Either[Throwable, FileWriter] =
    Try {
      new FileWriter(new File("src/main/scala/resources/sample.yaml"))
    }.toEither

  val writer: Either[Throwable, String] =
    for
      jsnValue <- jsonConfig
      fw <- fileWriter
    yield Try {
      fw.write(jsnValue.asYaml.spaces2)
      fw.close()
    }.fold(
      e => e.getMessage(),
      _ => "sample.yaml has been written"
    )
end YamlExample

@main
def program =
  import YamlExample.*
  // Reading a Yaml String
  printValue(processJson(ordersStringConfig))

  /** OrdersConfig(Orders String,Server(localhost,8080),List(Http, Grpc))
    */

  // Reading from a yaml File
  printValue(ordersFileConfig)

  /** OrdersConfig(Orders File,Server(localhost,8080),List(Http, Grpc))
    */

  ordersFileConfig2 match
    case Right(lst) =>
      lst.foreach(printValue)
    case Left(err) => println(err.getMessage)

  /** OrdersConfig(Orders,Server(localhost,8080),List(Http, Grpc))
    * OrdersConfig(Test,Server(localhost,9999),List(Http, Grpc))
    */

  // write yaml
  writer match
    case Right(v)  => println(v)
    case Left(err) => println(err.getMessage)

  /** sample.yaml has been written
    */
