package com.baeldung.scala.countchar

import scala.annotation.tailrec

object CountCharsInString {

  def countWithCount(string: String, char: Char): Int =
    string.count(_ == char)

  def countWithGroupBy(string: String, char: Char): Int =
    string.groupBy(identity).mapValues(_.map(_ => 1).reduce(_+_))(char)

  def countRecursive(string: String, char: Char): Int = {
    @tailrec
    def countRec(string: String, char: Char, count: Int): Int ={
      if(string.isEmpty) {
        count
      } else {
        val newCount = if(string.head == char) count + 1  else count
        countRec(string.tail, char, newCount)
      }
    }
    countRec(string, char, 0)
  }

  def countWithFilter(string: String, char: Char): Int = string.filter(_ == char).size
}
