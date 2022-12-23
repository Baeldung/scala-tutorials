package com.baeldung.scala.strings.camelcase

object StringWrapper {

  /** A value class that adds the `toCamelClass` method to strings.
    *
    * @param spacedString
    *   a string with spaces
    */
  implicit class CamelCaseWrapper(val spacedString: String) extends AnyVal {

    /** Transforms a spaced string to a camel-case string.
      *
      * @return
      *   a string in camel-case format
      */
    def toCamelCase: String = spacedString
  }
}
