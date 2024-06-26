package com.baeldung.scala.strings.checkempty

type Empty
type NonEmpty

opaque type VerifiedString[T] = String

object VerifiedString:
  def apply[T](value: String): VerifiedString[T] = value

  given Conversion[VerifiedString[NonEmpty], String] = _.value

  extension (str: String)
    def asVerifiedString =
      if str.isNullOrEmptyOrWhitespace then VerifiedString[Empty](str)
      else VerifiedString[NonEmpty](str)

    def isEmptyOrWhitespace: Boolean = str.trim.isEmpty
    def isNullOrEmptyOrWhitespace: Boolean =
      str == null || str.isEmptyOrWhitespace

  extension (vstr: VerifiedString[NonEmpty]) def value: String = vstr
