package com.baeldung.scala.typtag

import scala.reflect.runtime.universe
import scala.reflect.runtime.universe._

object WeakTypeTagExample {

  trait Foo {

    type Bar

    def barType: universe.Type = weakTypeTag[Bar].tpe
  }

}
