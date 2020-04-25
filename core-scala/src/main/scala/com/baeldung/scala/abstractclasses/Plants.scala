package com.baeldung.scala.abstractclasses

abstract class Plants {
  val leafColor: String = "green"
  def photosynthesis(): Boolean = leafColor == "green"
}

abstract class Tree(name: String) {
  val nickname: String = name
  val hasFruit: Boolean
  def swing(): Unit = println(s"Wheee I'm swinging on $nickname :)")
}

class Sunflower extends Plants {
  override val leafColor: String = "yellow"
}

class Oak(name: String) extends Tree(name) {
  val hasFruit: Boolean = false
}

class Watermelon extends Tree("Watery") {
  val hasFruit: Boolean = true
  override def swing(): Unit = println(s"I can't swing on $nickname :(")
}
