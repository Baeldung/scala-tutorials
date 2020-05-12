package com.baeldung.scala.extractorobject

class User(val name: String, val age: Int)

object User {
  def apply(name: String, age: Int) = new User(name, age)

  def apply(name: String) = new User(name, 0)

  def unapply(u: User): Option[(String, Int)] = Some(u.name, u.age)
}

object UserMainApp extends App {
  val user1: User = new User("John", 25)
  val user2: User = User.apply("John", 25)
  val user3: User = User("John", 25)

  val userArgs: Option[(String, Int)] = User.unapply(user1)

  user1 match {
    case User(_, age) if age < 18 =>
      println("You are not allowed to get a driver license.")
    case User(_, age) if age >= 18 =>
      println("You are allowed to get a driver's license.")
  }
}
