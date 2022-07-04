package com.baeldung

object Hello2 extends Greeting2 with App {
  println(greeting)
}

trait Greeting2 {
  lazy val greeting: String = "hello2"
}
