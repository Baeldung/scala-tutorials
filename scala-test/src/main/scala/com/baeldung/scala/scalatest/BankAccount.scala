package com.baeldung.scala.scalatest

class BankAccount(initialBalance: Double) {

  var balance = initialBalance

  def addToBalance(amount: Double): Unit =

    this.balance += amount

}

