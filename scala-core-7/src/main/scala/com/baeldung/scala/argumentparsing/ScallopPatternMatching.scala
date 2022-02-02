package com.baeldung.scala.argumentparsing

import org.rogach.scallop.ScallopConf

object ScallopPatternMatching {

  class Conf(args: Seq[String]) extends ScallopConf(args) {
    val propsMap = props[String]('P')
    val firstString = trailArg[String]()
    val firstList = trailArg[List[Int]]()
    val secondString = trailArg[String]()
    val secondList = trailArg[List[Double]]()
    verify()
  }

  def main(args: Array[String]): Unit = {
    val conf = new Conf(args)
    println("propsMap.key1 is: " + conf.propsMap("key1"))
    println("propsMap.key2 is: " + conf.propsMap("key2"))
    println("propsMap.key3 is: " + conf.propsMap("key3"))
    println("firstString is: " + conf.firstString())
    println("firstList is: " + conf.firstList())
    println("secondString is: " + conf.secondString())
    println("secondList is: " + conf.secondList())
  }
}
