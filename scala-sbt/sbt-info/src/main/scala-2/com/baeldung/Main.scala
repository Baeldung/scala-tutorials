package com.baeldung

import buindInfoArticle.BuildInfo

object Main {
  def main(args: Array[String]) {
    println("Application Build info: " + BuildInfo.toString)
    println("XML Application Build info: " + BuildInfo.toXML(BuildInfo.toMap))
  }
}
