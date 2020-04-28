package com.baeldung.scala.traitsvsclasses

import org.junit.Test
import org.junit.Assert.assertEquals

class HasConstantTitle(override val title: String) extends HasTitle

class CurlyBracesEncloser(inner: HasTitle) extends HasTitleEncloser(inner) {
  override def prefix: String = "("
  override def suffix: String = ")"
}

class AbstractClassDecoratorTest {
  @Test
    def abstractClassDecoratorWorksProperly() {
      val hasTitle = new HasConstantTitle("Hello, World!")
      val curlyHasTitle = new CurlyBracesEncloser(hasTitle)
      assertEquals(curlyHasTitle.title, "(Hello, World!)")
    }
}
