package com.baeldung.scala.traitsvsabstract

trait Base {
  def me: String = "Base"
  def printMe: Unit = println(me)
}

trait Super extends Base {
  override def me: String = super.me + "-Super"
}

trait Awesome extends Base {
  override def me: String = super.me + "-Awesome"
}

trait Fabulous extends Base {
  override def me: String = super.me + "-Fabulous"
}

object StackableSuperCalls {

  def main(args: Array[String]): Unit = {
    val superAwesome = new Base with Super with Awesome
    val awesomeFabulous = new Base with Awesome with Fabulous
    superAwesome.printMe
    awesomeFabulous.printMe
  }

}
