package com.baeldung.scala.scalatest

import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

class BDDBankTest extends AnyFeatureSpec with GivenWhenThen {

  Scenario("Cash is deposited into the account") {

    Given("the bank account has a balance of $30")
    val bankAccount = new BankAccount(30)

    When("$40 is added to the account balance")
    bankAccount.addToBalance(40)

    Then("there should be $70 in the account")
    assert(bankAccount.balance == 70)

  }
}