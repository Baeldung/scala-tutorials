package com.baeldung.csv.reader

import java.io.File

object CSVReaders {

  def main(args: Array[String]): Unit = {

    val apacheCommonsCSVReader = new ApacheCommonsCSVReader
    val simpleCSVReader = new SimpleCSVReader
    val scalaCSVReader = new ScalaCSVReader
    val openCSVReader = new OpenCSVReader

    val file = new File(getClass.getResource("/persons.csv").getFile)

    println(apacheCommonsCSVReader.read(file))
    println(simpleCSVReader.read(file))
    println(scalaCSVReader.read(file))
    println(openCSVReader.read(file))

  }

}
