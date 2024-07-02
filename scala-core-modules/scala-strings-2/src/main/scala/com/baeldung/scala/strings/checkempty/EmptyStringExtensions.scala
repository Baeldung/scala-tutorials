package com.baeldung.scala.strings.checkempty

object EmptyStringExtensions {
  extension (str: String)
    def isEmptyOrWhitespace: Boolean = str.trim.isEmpty
    def isNullOrEmptyOrWhitespace: Boolean =
      str == null || str.isEmptyOrWhitespace
}
