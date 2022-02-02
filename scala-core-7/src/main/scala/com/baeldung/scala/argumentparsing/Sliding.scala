package com.baeldung.scala.argumentparsing

import scala.sys.exit

object Sliding extends App {

  val usage = """
    Usage: sliding [--arg1 num] [--arg2 num] [--filename filename]
  """

  if (args.isEmpty || args.length % 2 != 0) {
    println(usage)
    exit(1)
  }

  val argMap = Map.newBuilder[String, Any]
  args.sliding(2, 2).toList.collect {
    case Array("--arg1", arg1: String) => argMap.+=("arg1" -> arg1)
    case Array("--arg2", arg2: String) => argMap.+=("arg2" -> arg2)
    case Array("--filename", filename: String) =>
      argMap.+=("filename" -> filename)
  }
  println(argMap.result())

}
