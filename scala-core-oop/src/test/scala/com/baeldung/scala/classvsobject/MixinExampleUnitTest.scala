package com.baeldung.scala.classvsobject

import com.baeldung.scala.classvsobject.MixinExample.{ProcessedData, TransformToStrAndInt}
import org.junit.Assert._
import org.junit.Test

class MixinExampleUnitTest {

  @Test
  def given_when_then(): Unit ={
    val t = new TransformToStrAndInt
    val transformed = t.transform("English is not hard if you are below 5 years. It takes only 60 days to master.")
    val strings = List("master.", "to", "days", "only", "takes", "It", "years.", "below", "are", "you", "if", "hard", "not", "is", "English")
    val integers = List(60, 5)

    assertTrue(transformed.isInstanceOf[ProcessedData])
    assertTrue(transformed.strings.isInstanceOf[List[String]])
    assertTrue(transformed.integers.isInstanceOf[List[Int]])
    assertEquals(transformed.strings, strings)
    assertEquals(transformed.integers, integers)
  }

}