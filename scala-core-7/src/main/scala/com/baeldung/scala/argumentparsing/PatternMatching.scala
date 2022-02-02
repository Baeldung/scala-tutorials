package com.baeldung.scala.argumentparsing

import scala.sys.exit

object PatternMatchingApp {

  val usage = """
    Usage: patternmatching [--arg1 num] [--arg2 num] filename
  """
  def main(args: Array[String]) {
    if (args.length == 0) println(usage)

    def nextArg(map: Map[String, Any], list: List[String]): Map[String, Any] = {
      list match {
        case Nil => map
        case "--arg1" :: value :: tail =>
          nextArg(map ++ Map("arg1" -> value.toInt), tail)
        case "--arg2" :: value :: tail =>
          nextArg(map ++ Map("arg2" -> value.toInt), tail)
        case string :: Nil =>
          nextArg(map ++ Map("filename" -> string), list.tail)
        case unknown :: _ =>
          println("Unknown option " + unknown)
          exit(1)
      }
    }
    val options = nextArg(Map(), args.toList)
    println(options)
  }

}
