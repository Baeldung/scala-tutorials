package com.baeldung.scala.bddtesting

class BankAccount(initialBalance: Double) {

  var balance = initialBalance

  def addToBalance(amount: Double): Unit =
    this.balance += amount

}
