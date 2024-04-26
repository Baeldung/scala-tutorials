package com.baeldung.scala.isomorphic

object IsomorphicStringsChecker:
  def checkIsomorphicBothWays(str1: String, str2: String): Boolean =
    (str1.length == str2.length) && checkIsomorphic(str1, str2) && checkIsomorphic(str2, str1)

  def checkIsomorphic2BothWays(str1: String, str2: String): Boolean =
    (str1.length == str2.length) && checkIsomorphic2(str1, str2) && checkIsomorphic2(str2, str1)

  private def checkIsomorphic(str1: String, str2: String): Boolean =
    val z = str1.zip(str2)
    val distinctCounts = z.map(tup => {
      z.distinct.count(tupZ => tupZ._1 == tup._1)
    })
    distinctCounts.count(_ > 1) == 0

  private def checkIsomorphic2(str1: String, str2: String): Boolean =
    val z = str1.zip(str2)
    val m = z.groupMap(_._1)(_._2)
    m.forall(_._2.distinct.length == 1)
