package com.baeldung.scala.strings.removemultispace

object RemoveMultipleSpaces {
  def usingReplaceAll(str: String): String = {
    str.trim.replaceAll("\\s+", " ")
  }

  def usingSplit(str: String): String = {
    str.trim.split("\\s+").mkString(" ")
  }

  def usingZip(str: String): String = {
    if (str.trim.isEmpty) {
      str.trim
    } else {
      val zipped = str.trim.zip(str.trim.tail)
      str.trim.head + zipped.collect {
        case (a, b) if !(a == ' ' && b == ' ') => b
      }.mkString
    }
  }

  def usingStringBuilder(str: String): String = {
    val sb = new StringBuilder
    var lastCharWasSpace = false

    for (c <- str.trim) {
      if (c != ' ' || !lastCharWasSpace) sb.append(c)
      lastCharWasSpace = c == ' '
    }
    sb.toString
  }

}
