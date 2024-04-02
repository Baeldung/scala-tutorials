package com.baeldung.scala.isomorphic

object IsomorphicStringsChecker {
  def checkIsomorphicBothWays(str1: String, str2: String): Boolean =
    if (str1.length == str2.length)
      checkIsomorphic(str1, str2) && checkIsomorphic(str2, str1)
    else
      false


  private def checkIsomorphic(str1: String, str2: String): Boolean =
    val z = str1.zip(str2)
    val distinctCounts = z.map(tup => {
      z.distinct.count(tupZ => tupZ._1 == tup._1)
    })

    if (distinctCounts.count(_ > 1) == 0)
      true
    else
      false

}
