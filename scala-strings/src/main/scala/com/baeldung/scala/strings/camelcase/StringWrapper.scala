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
    def toCamelCase: String = useMapReduce(spacedString)
  }

  val useMapReduce: String => String = { spacedString =>
    val first :: rest =
      spacedString.split(Array(' ', '_')).toList.map(_.toLowerCase)
    val changedRest = rest.map(w => w.take(1).toUpperCase.concat(w.drop(1)))
    val reunited = first :: changedRest
    reunited.foldLeft("")((a, b) => a + b)
  }

}
