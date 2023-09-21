package com.baeldung.circeYaml

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import YamlExample.*
import io.circe.*
import java.io.*
import org.scalatest.prop.TableDrivenPropertyChecks

class YamlExampleSuite
  extends AnyFlatSpec
  with Matchers
  with TableDrivenPropertyChecks:
  val myServer = Server("localhost", 8080)
  val myOC = OrdersConfig("test", myServer, List("Http", "Grpc"))

  "Server" should "contain a host String and port Int" in {
    myServer.host shouldBe an[String]
    myServer.port shouldBe an[Int]
  }

  "OrdersConfig" should "contain a name String, Server, and serverType, List[String]" in {
    myOC.name shouldBe an[String]
    myOC.server shouldBe an[Server]
    myOC.serverType shouldBe an[List[String]]
  }

  "ordersStringConfig" should "be of type Either[ParsingFailure, Json]" in {
    ordersStringConfig shouldBe an[Either[ParsingFailure, Json]]
  }

  "processJson()" should "return an Either[Error, OrdersConfig]" in {
    processJson(ordersStringConfig) shouldBe an[Either[Error, OrdersConfig]]
  }

  "printValue" should "return Unit" in {
    printValue(processJson(ordersStringConfig)) shouldBe an[Unit]
  }

  "yamlFileReader and yamlFileReader2" should "be of type Either[Throwable, FileReader]" in {
    forAll(Table("Either File Reader", yamlFileReader, yamlFileReader2)) { v =>
      v shouldBe an[Either[Throwable, FileReader]]
    }
  }

  "ordersFileConfig2" should "be of type Either[Throwable, List[Either[Error, OrdersConfig]]]" in {
    ordersFileConfig2 shouldBe an[
      Either[Throwable, List[Either[Error, OrdersConfig]]]
    ]
  }

  "jsonString" should "be of type String" in {
    jsonString shouldBe an[String]
  }

  "fileWriter" should "be of type Either[Throwable, FileWriter]" in {
    fileWriter shouldBe an[Either[Throwable, FileWriter]]
  }

  "writer" should "be of type Either[Throwable, String]" in {
    writer shouldBe an[Either[Throwable, String]]
  }

end YamlExampleSuite
