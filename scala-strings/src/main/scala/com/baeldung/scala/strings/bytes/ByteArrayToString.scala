package com.baeldung.scala.strings.bytes

import java.nio.charset.StandardCharsets

object ByteArrayToString {
  def main(args: Array[String]): Unit = {
    val helloInUtf16 = Array[Byte](104, 101, 108, 108, 111)
    val helloInUtf16Le = Array[Byte](104, 0, 101, 0, 108, 0, 108, 0, 111, 0)

    usingToString(helloInUtf16)
    usingNewString(helloInUtf16)
    usingToChar(helloInUtf16)
    usingDifferentCharSet(helloInUtf16Le)
  }

  def usingToString(bytes: Array[Byte]) = {
    bytes.toString()
  }

  def usingNewString(bytes: Array[Byte]) = {
    new String(bytes, StandardCharsets.UTF_8)
  }

  def usingToChar(bytes: Array[Byte]) = {
    bytes.map(_.toChar).mkString
  }

  def usingDifferentCharSet(bytes: Array[Byte]) = {
    new String(bytes, StandardCharsets.UTF_16LE)
  }

}
