package com.baeldung.scala.scalafix

object DisableSyntaxDemo:
  var myVariable = null

  def validateMyVariable(): Boolean =
    if (myVariable == null) throw Exception("myVariable Is Null")

    return true

object DisableSyntaxDemoRewritten:
  val myVariable = Option.empty[Unit]

  def validateMyVariable(): Either[String, Unit] =
    myVariable.toRight("myVariable Is Null")
