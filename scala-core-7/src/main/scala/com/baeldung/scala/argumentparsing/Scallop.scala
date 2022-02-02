package com.baeldung.scala.argumentparsing

import org.rogach.scallop._

object Scallop {

  class Conf(arguments: Seq[String]) extends ScallopConf(arguments) {
    val high = opt[Int](required = true)
    val low = opt[Int]()
    val name = trailArg[String]()
    verify()
  }

  def main(args: Array[String]): Unit = {
    val conf = new Conf(args)
    println("high is: " + conf.high())
    println("low is: " + conf.low())
    println("name is: " + conf.name())
  }

}
