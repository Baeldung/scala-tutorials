package com.baeldung.scala3.matchexpressions

object MatchExpressionImprovements {
  def wordFromOptionNoBraces(strOpt: Option[String]) = {
    strOpt match
      case None      => "Option is None"
      case Some(str) => s"Option contains $str"
  }
  def wordFromOption(strOpt: Option[String]) = {
    strOpt match {
      case None      => "empty"
      case Some(str) => str
    } match {
      case "empty" => "Option is None"
      case string  => s"Option contains $string"
    }
  }
  def isOptionEmpty(strOpt: Option[String]) = {
    strOpt.match
      case None    => true
      case Some(_) => false
  }
  def optionContains(strOpt: Option[String]) = {
    if strOpt.match
        case None    => true
        case Some(_) => false
    then "nothing"
    else "A string"
  }
  def isOptionEmptyWithType(strOpt: Option[String]) = {
    (strOpt: Option[String]) match
      case None    => true
      case Some(_) => false
  }
}
