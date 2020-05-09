package com.baeldung.scala.abstractclasstrait.demo

class Cat {

  trait hunt {
    println("Hun mouse")
  }

  object CatObject extends App {
    var cat = new Cat with hunt
  }

}
