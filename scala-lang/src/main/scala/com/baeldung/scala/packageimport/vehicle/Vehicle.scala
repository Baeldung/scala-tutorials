package com.baeldung.scala.packageimport.vehicle {

  import java.sql.{Date => sqlDate}
  import java.util.{Date => utilDate}

  abstract class Vehicle(numberOfTires: Int, brand: String) {

    def run(): Unit = {
      val dt: utilDate = new utilDate()
      val dtSql: sqlDate = new sqlDate(System.currentTimeMillis())
      println(s"I am a vehicle running on $dt !")
      println(s"I am a vehicle running on $dtSql !")
    }
  }

}

package com.baeldung.scala.packageimport.vehicleNew {

  import java.sql.{Date => sqlDate}

  abstract class Vehicle(numberOfTires: Int, brand: String) {

    def run(): Unit = {
      val dtSql: sqlDate = new sqlDate(System.currentTimeMillis())
      println(s"I am a NEW vehicle running on $dtSql !")
    }
  }

}
