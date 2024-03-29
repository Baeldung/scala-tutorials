package com.baeldung.scala.strings.definition

object DefiningStringsExamples {

  def main(args: Array[String]): Unit = {

    val singleLineString = "Hello, I am a single line string"
    val multiLineString = """Hello, I am
                            |a multiline
                            |String""".stripMargin

    println(singleLineString)
    println(multiLineString)

    println("Hello world" == singleLineString)
    println(null == singleLineString)
    assert("Hello, I am a single line string" == singleLineString)

    val askTheTimeString = "...What time is it?"

    val concatStrWithConcat = singleLineString.concat(askTheTimeString)
    assert(
      concatStrWithConcat == "Hello, I am a single line string...What time is it?"
    )

    val concatStr = singleLineString + askTheTimeString
    assert(concatStr == "Hello, I am a single line string...What time is it?")

    val age = 30
    val agePresentation = s"I am $age"

    val height = 1.7d
    val name = "Michele"
    println(
      f"My name is $name%s. I am $age%d years old and $height%1.2f meters tall"
    )

    println(
      raw"My name is $name%s. \n $agePresentation and $height%1.2f meters tall"
    )
    println(
      f"My name is $name. \n $agePresentation and $height%1.2f meters tall"
    )
  }
}
