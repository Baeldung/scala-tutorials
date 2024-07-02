package com.baeldung.scala.strings.checkempty

object EmptyPatternMatchingExtensions {
  extension (seq: Seq[?])
    def isNullOrEmpty: Boolean = seq match {
      case null  => true
      case Seq() => true
      case s     => false
    }

  extension (seq: Seq[Char])
    def isNullOrEmptyOrWhitespace: Boolean = seq match {
      case null  => true
      case Seq() => true
      case s     => s.forall(_.isWhitespace)
    }

  extension (str: String)
    def isNullOrEmptyOrWhitespace: Boolean = str match {
      case null => true
      case ""   => true
      case s    => s.trim.isEmpty
    }

}
