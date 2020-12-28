package com.baeldung.scala.pathdependenttypes

object AwardPunishmentDiscipline extends App {
  val john = Parent("John")
  val scarlet = Parent("Scarlet")
  john.punish(john.child)
 
  // Compile time error:
  // john.punish(scarlet.car)
}

case class Parent(name: String) {
  class Child

  def child = new this.Child

  def punish(c: this.Child): Unit =
    println(s"$name is punishing $c")

  def reward(c: Parent#Child): Unit =
    println(s"$name is rewarding $c")
}
