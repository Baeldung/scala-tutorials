package com.baeldung.scala

object AbstractClassTraitsEx {

  // creating a Trait
  trait AccountTrait {
    var balance = 0

    // it is an Abstract method. It need to be implemented in it's sub class for facilitating the respective feature
    def deposit(amount: Int)

    // Non-Abstract method
    def printBalance() {
      println("Total balance AccountTrait : " + balance)
    }
  }

  // creating an abstract class
  abstract class AccountAbstract {
    var balance = 0

    // it is an Abstract method. It need to be implemented in it's sub class for facilitating the respective feature
    def deposit(amount: Int)

    // Non-Abstract method
    def printBalance() {
      println("Total balance in AccountAbstract : " + balance)
    }
  }

  class TraitImplementor extends AccountTrait { // extending traits
    // implementing abstract method
    override def deposit(amount: Int) =
      balance = balance + amount
  }

  class AbstractClassImplementor extends AccountAbstract { // extending abstract class
    // implementing abstract method
    override def deposit(amount: Int): Unit =
      balance = balance + amount
  }
  def main(args: Array[String]): Unit = {
    val traitObj: AccountTrait = new TraitImplementor
    traitObj.deposit(100)
    traitObj.printBalance()

    val abstractObj: AccountAbstract = new AbstractClassImplementor
    abstractObj.deposit(200)
    abstractObj.printBalance()
  }
}
