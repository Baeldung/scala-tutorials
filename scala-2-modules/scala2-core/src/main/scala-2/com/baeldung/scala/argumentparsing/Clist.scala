package com.baeldung.scala.argumentparsing

import org.backuity.clist.{Cli, Command, arg, opt}

class Config extends Command("") {
  var apples = arg[Int](description = "apples count")
  var oranges = opt[Option[Int]](description = "oranges count")
  var debug = opt[Boolean](description = "debug flag", abbrev = "d")
  var list = opt[Option[Seq[String]]](description = "a list of strings")
}

object Clist {
  def main(args: Array[String]): Unit = {
    Cli.parse(args).withCommand(new Config) { config =>
      println(config.description)
    }
  }
}
