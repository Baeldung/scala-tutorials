package com.baeldung.native

import scalanative._
import scala.scalanative.unsafe._
import scala.scalanative.libc.string

object NativeApp {

  def printFromNative() =
    println(fansi.Color.Green("Hello, world from Scala-Native!"))

  def simpleNative() = {
    val s1 = "Scala"
    val s2 = "Native"
    val scalaNative: String = Zone { implicit z =>
      val scalaNativeC: CString = string.strcat(toCString(s1), toCString(s2))
      println(fromCString(scalaNativeC)) // Prints ScalaNative
      fromCString(scalaNativeC)
    }
    val strLength: CSize = string.strlen(c"Hello ScalaNative")
    println(s"Length of string is: " + strLength)
  }

  def nativeFromCFile() = {
    sample.check_length(c"Hello ScalaNative")
  }

  def testCurl() = {
    testcurl.make_curl_call(c"http://httpbin.org/uuid")
  }

  @extern
  object sample {
    def check_length(str: CString): Int = extern
  }

  @extern
  @link("curl")
  object testcurl {
    def make_curl_call(str: CString): Int = extern
  }

  def main(args: Array[String]): Unit = {
    printFromNative()
    simpleNative()
    nativeFromCFile()
    testCurl()
  }

}
