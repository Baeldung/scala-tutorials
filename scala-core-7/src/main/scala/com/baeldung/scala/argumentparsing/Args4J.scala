package com.baeldung.scala.argumentparsing

import org.kohsuke.args4j.{CmdLineException, CmdLineParser, Option}

import scala.collection.JavaConverters
import scala.sys.exit

object Args {
  @Option(name = "-bananas", required = true, usage = "bananas count")
  var bananas: Int = -1

  @Option(name = "-apples", usage = "apples count")
  var apples: Int = -1

  @Option(name = "-filename", usage = "file name")
  var filename: String = null

}

object Args4J {

  def main(args: Array[String]): Unit = {
    val parser = new CmdLineParser(Args)
    try {
      parser.parseArgument(JavaConverters.asJavaCollection(args))
    } catch {
      case e: CmdLineException =>
        print(s"Error:${e.getMessage}\n Usage:\n")
        parser.printUsage(System.out)
        exit(1)
    }
    println(Args.apples)
    println(Args.bananas)
    println(Args.filename)
  }

}
