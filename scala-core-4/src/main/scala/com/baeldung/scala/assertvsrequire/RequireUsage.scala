package com.baeldung.scala.assertvsrequire

object RequireUsage extends App {

  def issueDrivingLicense(name: String, age: Int): Unit = {
    require(age >= 18)
    println("Issued Driving License to %s ".format(name))
  }

  issueDrivingLicense("Drawin", 38)

  try {
    issueDrivingLicense("Drawin Jr", 5)
  } catch {
    case e: IllegalArgumentException =>
      println("Failed in require precondition")
  }

}
