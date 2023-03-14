package com.baeldung.scala.bddtesting

import org.scalatest.GivenWhenThen
import org.scalatest.funspec.AnyFunSpec

class BDDBankUnitTest extends AnyFunSpec with GivenWhenThen {

  describe("A bank account") {

    it("should have money deposited into it") {

      Given("the bank account has a balance of $30")
      val bankAccount = new BankAccount(30)

      When("$40 is added to the account balance")
      bankAccount.addToBalance(40)

      Then("there should be $70 in the account")
      assert(bankAccount.balance == 70)
    }
  }
}
