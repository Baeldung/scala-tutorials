package com.baeldung.reflection

case class Person(name: String, age: Int) extends PersonInterface {
  def prettyPrint =
    s"""
       |Person {
       |  name: "$name",
       |  age: $age
       |}
       |""".stripMargin

  
}
