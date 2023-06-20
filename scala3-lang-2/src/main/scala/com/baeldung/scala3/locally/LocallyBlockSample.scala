package com.baeldung.scala3.locally

object LocallyBlockSampleDangling extends App {
  class MyClass

  //format: off
  lazy val myClass = new MyClass
  {
    println("This should be an independant block")
  }
  //format: on

  //format: off
  lazy val myClass2 = new MyClass

  {
    println("*** This should be an independant block ***")
  }
  //format: on

}

object LocallyBlockSampleLocally extends App {
  class MyClass

  //format: off
  lazy val myClass = new MyClass
  locally {
    println("This should be an independant block using locally")
  }
  //format: on

  println("Performing some set of actions")
  locally {
    val a = 100
    println("Inside locally block, performing something")
    // do something
    val b = 200
    println(s"starting some background process using $a and $b")
  }

  def myMethod = {
    val step1 = "some complicated step 1"
    val step2 = locally {
      // this step includes a lot of complicated logic
      // doing this first
      val a = 100
      // doing second next complicated part
      val b = 200
      // some more complicated operation
      a + b
    }
  }

}
