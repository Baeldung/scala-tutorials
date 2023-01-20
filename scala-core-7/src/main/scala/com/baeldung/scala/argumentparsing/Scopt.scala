package com.baeldung.scala.argumentparsing

import scopt.OParser

import scala.sys.exit

object Scopt {

  case class Config(
    argA: Int = -1,
    argB: Int = -1,
    debug: Boolean = false,
    string: String = "",
    list: Seq[String] = Seq(),
    map: Map[String, String] = Map(),
    command1: String = "",
    cmdArg: Int = 0
  )

  val builder = OParser.builder[Config]
  val argParser = {
    import builder._
    OParser.sequence(
      programName("myprog"),
      head("myprog", "0.1"),
      opt[Int]('a', "argA")
        .required()
        .action((a, c) => c.copy(argA = a))
        .text("required integer"),
      opt[Int]('b', "argB")
        .action((b, c) => c.copy(argB = b))
        .validate(b => {
          if (b >= 10) {
            success
          } else {
            failure("just cause")
          }
        })
        .text("optional integer"),
      opt[Boolean]('d', "debug")
        .action((d, c) => c.copy(debug = d))
        .text("optional boolean"),
      opt[String]('s', "string")
        .action((s, c) => c.copy(string = s))
        .text("optional string"),
      opt[Seq[String]]('l', "list")
        .valueName("<v1>,<v2>")
        .action((l, c) => c.copy(list = l))
        .text("string list"),
      opt[Map[String, String]]('m', "map")
        .valueName("<k1>=<v1>,<k2>=<v2>")
        .action((m, c) => c.copy(map = m))
        .text("optional map"),
      cmd("command1")
        .action((_, c) => c.copy(command1 = "command1"))
        .children(
          opt[Int]("cmdArg")
            .action((cmdArg, c) => c.copy(cmdArg = cmdArg))
            .text("command argument")
        ),
      checkConfig(c => {
        if (c.argA < c.argB) {
          success
        } else {
          failure("just cause")
        }
      })
    )

  }

  def main(args: Array[String]): Unit = {
    OParser.parse(argParser, args, Config()) match {
      case Some(_) =>
        println(OParser.usage(argParser))
      // do stuff with config
      case _ =>
        exit(1)
    }
  }

}
