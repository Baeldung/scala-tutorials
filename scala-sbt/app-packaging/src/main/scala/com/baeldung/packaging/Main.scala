package com.baeldung.packaging
import os._

@main def mainMethod() =
  val osName = System.getProperty("os.name")
  val path = os.pwd.toString
  println(s"""
    | Hello from the packaged app! 
    | Current Path: ${path}
    """.stripMargin)
