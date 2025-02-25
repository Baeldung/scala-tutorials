package com.baeldung.csv.reader

object CSVReaders {

  def main(args: Array[String]): Unit = {

    val apacheCommonsCSVReader = new ApacheCommonsCSVReader
    val simpleCSVReader = new SimpleCSVReader
    val scalaCSVReader = new ScalaCSVReader
    val openCSVReader = new OpenCSVReader

    println(apacheCommonsCSVReader.read("persons.csv"))
    println(simpleCSVReader.read("persons.csv"))
    println(scalaCSVReader.read("persons.csv"))
    println(openCSVReader.read("persons.csv"))

  }

}
