package com.baeldung.scala.isomorphic

object IsomorphicStringsChecker:
  def checkIsomorphicBothWays(str1: String, str2: String): Boolean =
    if (str1.length == str2.length)
      checkIsomorphic(str1, str2) && checkIsomorphic(str2, str1)
    else
      false

  def checkIsomorphic2BothWays(str1: String, str2: String): Boolean =
    if (str1.length == str2.length)
      checkIsomorphic2(str1, str2) && checkIsomorphic2(str2, str1)
    else
      false

  private def checkIsomorphic(str1: String, str2: String): Boolean =
    val z = str1.zip(str2)
    // for each distinct pair, count the number of occurrences of tup._1 char
    val distinctCounts = z.map(tup => {
      z.distinct.count(tupZ => tupZ._1 == tup._1)
    })
    // if all occurrence occur once only
    if (distinctCounts.count(_ > 1) == 0)
      true
    else
      false

  private def checkIsomorphic2(str1: String, str2: String): Boolean =
    val z = str1.zip(str2)
    val m = z.groupMap(_._1)(_._2)
    // println(m)
    if (m.forall(_._2.distinct.length == 1))
      true
    else
      false
