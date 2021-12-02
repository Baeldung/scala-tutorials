package com.baeldung.scala.collections

object IterateMap {
  val asciiConversion = Map(
    'a' -> 97,
    'b' -> 98,
    'c' -> 99,
    'd' -> 100
  )

  val iterateWithCase: Iterable[String] = asciiConversion.map { case (key, value) => s"Key: $key, Value: $value" }

  val iterateWithTuple: Iterable[String] = asciiConversion.map(pair => s"Key: ${pair._1}, Value: ${pair._2}")

  val iterateWithForTuple: Iterable[String] = for (pair <- asciiConversion) yield s"${pair._1} : ${pair._2}"

  val iterateWithForSplit: Iterable[String] = for ((key, value) <- asciiConversion) yield s"$key : $value"

  val values: Iterable[Int] = asciiConversion.values

  val keys: Iterable[Char] = asciiConversion.keys
}
