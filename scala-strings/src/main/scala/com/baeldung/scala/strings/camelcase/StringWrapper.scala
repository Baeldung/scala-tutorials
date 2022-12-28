package com.baeldung.scala.strings.camelcase

object StringWrapper {

  /** A value class that adds the `toCamelCase` method to strings.
    *
    * @param spacedString
    *   a string with spaces
    */
  implicit class CamelCaseWrapper(val spacedString: String) extends AnyVal {

    /** Transforms a spaced string to a camelCase string.
      *
      * @return
      *   a string in camelCase format
      */
    def toCamelCase: String = useMapReduce(spacedString)
  }

  val useMapReduce: String => String = { spacedString =>
    val first :: rest =
      spacedString.split(Array(' ', '_')).toList.map(_.toLowerCase)
    val changedRest = rest.map(w => w.take(1).toUpperCase + w.drop(1))
    val reunited = first :: changedRest
    reunited.mkString
  }

  val useStreams: String => String = { spacedString =>
    val first = spacedString.take(1).toLowerCase
    val rest =
      spacedString.toStream.sliding(2).foldLeft("") { (str, charStream) =>
        val added = charStream.toList match {
          case ' ' :: ' ' :: _ => ""
          case ' ' :: '_' :: _ => ""
          case '_' :: ' ' :: _ => ""
          case '_' :: '_' :: _ => ""
          case ' ' :: other    => other.mkString.toUpperCase
          case '_' :: other    => other.mkString.toUpperCase
          case _ :: other :: _ if other != ' ' && other != '_' =>
            other.toLower.toString
          case _ => ""
        }
        str + added
      }
    first + rest
  }

}
