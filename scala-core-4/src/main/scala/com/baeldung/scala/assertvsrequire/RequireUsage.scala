package com.baeldung.scala.assertvsrequire

object RequireUsage extends App {

  def issueDrivingLicense(name: String, age: Int): Unit = {
    require(age >= 18)
    println("Issued Driving License to %s ".format(name))
  }
  issueDrivingLicense("Darwin", 38) // prints "Issued Driving License to Darwin"

  try {
    issueDrivingLicense("Jr. Darwin", 5) // prints "Failed in require precondition"
  } catch {
    case e: IllegalArgumentException =>
      println("Failed in require precondition-"+e.getMessage)
  }

}
