package com.baeldung.scala.traits.classes

object AbstractClassesAndTraits {

  trait Log {
    implicit val logTag: String = getClass.getSimpleName
  }

  abstract class Product(name: String, id: Int) {
    def productCode(): String = s"$name:$id"
  }

  class Chair extends Product("chair", 1)
  class vase extends Product("vase", 2)
}
