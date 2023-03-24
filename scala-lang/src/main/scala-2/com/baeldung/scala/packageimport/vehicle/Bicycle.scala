package com.baeldung.scala.packageimport.vehicle

import java.sql.Date
import java.util.{Date => _}

class Bicycle(numberOfTires: Int, brand: String)
  extends Vehicle(numberOfTires, brand) {
  override def run(): Unit = {
    super.run()
    val dtSql: Date = new Date(System.currentTimeMillis())
    println(
      s"$numberOfTires tires from the bicycle brand $brand are running on $dtSql"
    )
  }
}
