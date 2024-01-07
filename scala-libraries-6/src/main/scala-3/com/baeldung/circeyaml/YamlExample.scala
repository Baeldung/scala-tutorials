//package com.baeldung.circeyaml
//
//import io.circe.{Error, Json, ParsingFailure, yaml}
//
//import java.io.{File, FileReader, FileWriter}
//import java.nio.file.Paths
//import scala.util.Try
//
//object YamlExample:
//  case class Server(host: String, port: Int)
//  case class OrdersConfig(
//    name: String,
//    server: Server,
//    serverType: List[String]
//  )
//
//  val ordersYamlConfig: String =
//    """
//      name: Orders String
//      server:
//          host: localhost
//          port: 8080
//      serverType: 
//          - Http
//          - Grpc
//    """
//  val ordersStringConfig: Either[ParsingFailure, Json] =
//    yaml.parser.parse(ordersYamlConfig)
//
//  def processJson(
//    json: Either[ParsingFailure, Json]
//  ): Either[Error, OrdersConfig] =
//    json
//      .leftMap(err => err: Error)
//      .flatMap(_.as[OrdersConfig])
//
//  def printValue(value: Either[Error | Throwable, OrdersConfig]) =
//    value match
//      case Right(v)  => println(v)
//      case Left(err) => println(err.getMessage)
//
//  // Reading a yaml file
//  val yamlFileReader: Either[Throwable, FileReader] =
//    Try {
//      new FileReader(
//        "src/main/scala/resources/orders.yaml"
//      )
//    }.toEither
//
//  val ordersFileConfig: Either[Throwable, OrdersConfig] =
//    yamlFileReader
//      .map(fileReader => processJson(yaml.parser.parse(fileReader)))
//      .flatten
//
//  // Reading multiple yaml documents in a single file.
//  val yamlFileReader2: Either[Throwable, FileReader] =
//    Try {
//      new FileReader(
//        "src/main/scala/resources/service.yaml"
//      )
//    }.toEither
//
//  val ordersFileConfig2: Either[Throwable, List[Either[Error, OrdersConfig]]] =
//    yamlFileReader2
//      .map(fileReader => yaml.parser.parseDocuments(fileReader).toList)
//      .map(_.map(processJson))
//
//  def fileWriter(path: String): Either[Throwable, FileWriter] =
//    Try {
//      new FileWriter(new File(path))
//    }.toEither
//
//  def writeYaml(jsnValue: Json, fw: FileWriter, path: String): String =
//    Try {
//      fw.write(jsnValue.asYaml.spaces2)
//      fw.close()
//    }.fold(
//      e => e.getMessage(),
//      _ => s"${Paths.get(path).getFileName().toString()} has been written"
//    )
//
//  val jsonString =
//    """
//      {
//        "name": "Orders Json",
//        "server":
//          {
//            "host": "localhost",
//            "port": 8080
//          },
//        "serverType": ["Http", "Grpc"]
//      }
//    """
//
//  def writeJsonStr(path: String, jsonStr: String): Either[Throwable, String] =
//    for
//      jsnValue <- parse(jsonString)
//      fw <- fileWriter(path)
//    yield writeYaml(jsnValue, fw, path)
//
//  val myCaseClass =
//    OrdersConfig("Orders", Server("localhost", 8080), List("Http", "Grpc"))
//
//  def writeOrdersConfig(path: String, oc: OrdersConfig): String =
//    fileWriter(path) match
//      case Right(fw) => writeYaml(oc.asJson, fw, path)
//      case Left(err) => err.getMessage
//
//end YamlExample
