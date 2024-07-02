package com.baeldung.scala.uniformcase

object UniformCaseChecker {

  def convertAndCheck(str: String): Boolean = {
    str.toUpperCase == str || str.toLowerCase == str
  }

  def isUpperLowerAndForAll(str: String): Boolean = {
    val filteredStr = str.filter(_.isLetter)
    filteredStr.forall(_.isUpper) || filteredStr.forall(_.isLower)
  }

  def regexCheck(str: String): Boolean = {
    val filteredStr = str.filter(_.isLetter)
    filteredStr.matches("^[A-Z]*$") || filteredStr.matches("^[a-z]*$")
  }

  def countAndCheck(str: String): Boolean = {
    val filteredStr = str.filter(_.isLetter)
    filteredStr.count(_.isUpper) == 0 || filteredStr.count(_.isLower) == 0
  }

}
