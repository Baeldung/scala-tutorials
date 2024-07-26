package com.baeldung.scala.strings.cameltosnake

import scala.annotation.tailrec

object CamelToSnakeConversion {

  def usingRegex(camelCaseString: String): String = {
    val regex = "([A-Z])".r
    regex.replaceAllIn(
      camelCaseString,
      m => s"_${m.group(1).toLowerCase}"
    )
  }

  def usingFoldLeft(camelCaseString: String): String = {
    camelCaseString.foldLeft("") { (acc, char) =>
      if (char.isUpper) {
        if (acc.isEmpty) char.toLower.toString
        else acc + "_" + char.toLower
      } else {
        acc + char
      }
    }
  }

  def handleAcronymsWithRegex(camelCaseString: String): String = {
    camelCaseString
      .replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
      .replaceAll("([a-z\\d])([A-Z])", "$1_$2")
      .toLowerCase
  }

  def usingFlatMap(camelCase: String): String = {
    camelCase
      .flatMap { c =>
        if (c.isUpper) List('_', c) else List(c)
      }
      .mkString
      .toLowerCase
  }

  def usingPatternMatching(camelCase: String): String = {
    @tailrec
    def rec(chars: List[Char], acc: List[Char]): List[Char] = {
      chars match {
        case Nil                    => acc
        case a :: tail if a.isUpper => rec(tail, acc ++ Seq('_', a))
        case a :: tail              => rec(tail, acc ++ Seq(a))
      }
    }
    rec(camelCase.toList, Nil).mkString.toLowerCase
  }

  def usingCollect(camelCase: String): String = {
    camelCase
      .collect {
        case c if c.isUpper => Seq('_', c)
        case c              => Seq(c)
      }
      .flatten
      .mkString
      .toLowerCase
  }

}
