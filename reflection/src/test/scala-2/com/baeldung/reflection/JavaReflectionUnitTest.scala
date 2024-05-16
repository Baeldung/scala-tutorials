package com.baeldung.reflection

import org.junit.Test

class JavaReflectionUnitTest {
  @Test
  def invoke_method_dynamically(): Unit = {
    val person = Person("John", 20)
    val result = classOf[Person].getDeclaredMethod("prettyPrint").invoke(person)
    assert(
      result ==
        s"""
         |Person {
         |  name: "John",
         |  age: 20
         |}
         |""".stripMargin
    )
  }
}
