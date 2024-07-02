package com.baeldung.scala.strings.checkempty

object EmptySeqExtensions {
  extension (objs: Seq[?])
    def isNullOrEmpty: Boolean = objs == null || objs.isEmpty

  extension (objs: Seq[Char])
    def isNullOrEmptyOrWhitespace: Boolean =
      objs.isNullOrEmpty || objs.forall(_.isWhitespace)
}
