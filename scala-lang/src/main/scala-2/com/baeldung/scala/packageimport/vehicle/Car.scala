package com.baeldung.scala.packageimport.vehicle

import com.baeldung.scala.packageimport.Motor

class Car(numberOfTires: Int, brand: String, isDiesel: Boolean)
  extends com.baeldung.scala.packageimport.vehicleNew.Vehicle(
    numberOfTires,
    brand
  )
  with Motor {
  override def run(): Unit = {
    super.run()
    println(
      s"$numberOfTires tires from the brand $brand are running. It is a diesel motor: $isDiesel."
    )
    if (isDiesel)
      println(dieselMessage)
  }
}
