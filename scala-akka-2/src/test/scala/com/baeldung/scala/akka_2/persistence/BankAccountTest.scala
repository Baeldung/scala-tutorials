package com.baeldung.scala.akka_2.persistence

import akka.actor.testkit.typed.scaladsl.ScalaTestWithActorTestKit
import akka.pattern.StatusReply
import org.scalatest.wordspec.AnyWordSpecLike

class BankAccountTest extends ScalaTestWithActorTestKit with AnyWordSpecLike {

  "A Bank Account" should {
    "deposit money to a bank account" in {
      val bankAccount = testKit.spawn(BankAccount(1))
      val probe = createTestProbe[StatusReply[BankAccount.Response]]
      bankAccount ! BankAccount.BalanceCheckCommand(probe.ref)
      probe.expectMessage(
        StatusReply.Success(BankAccount.BalanceCheckResponse(0))
      )
      bankAccount ! BankAccount.DepositCommand(100, probe.ref)
      probe.expectMessage(
        StatusReply.Success(
          BankAccount.ActionResponse("Amount 100 was deposited")
        )
      )
      bankAccount ! BankAccount.BalanceCheckCommand(probe.ref)
      probe.expectMessage(
        StatusReply.Success(BankAccount.BalanceCheckResponse(100))
      )
      testKit.stop(bankAccount)
    }

    "withdraw money from a bank account" in {
      val bankAccount = testKit.spawn(BankAccount(2))
      val probe = createTestProbe[StatusReply[BankAccount.Response]]
      bankAccount ! BankAccount.BalanceCheckCommand(probe.ref)
      probe.expectMessage(
        StatusReply.Success(BankAccount.BalanceCheckResponse(0))
      )
      bankAccount ! BankAccount.DepositCommand(100, probe.ref)
      probe.expectMessage(
        StatusReply.Success(
          BankAccount.ActionResponse("Amount 100 was deposited")
        )
      )
      bankAccount ! BankAccount.BalanceCheckCommand(probe.ref)
      probe.expectMessage(
        StatusReply.Success(BankAccount.BalanceCheckResponse(100))
      )
      bankAccount ! BankAccount.WithdrawCommand(20, probe.ref)
      probe.expectMessage(
        StatusReply.Success(
          BankAccount.ActionResponse("Amount 20 was withdrawn")
        )
      )
      bankAccount ! BankAccount.BalanceCheckCommand(probe.ref)
      probe.expectMessage(
        StatusReply.Success(BankAccount.BalanceCheckResponse(80))
      )
      testKit.stop(bankAccount)
    }

    "fail to withdraw money from a bank account" in {
      val bankAccount = testKit.spawn(BankAccount(3))
      val probe = createTestProbe[StatusReply[BankAccount.Response]]
      bankAccount ! BankAccount.BalanceCheckCommand(probe.ref)
      probe.expectMessage(
        StatusReply.Success(BankAccount.BalanceCheckResponse(0))
      )
      bankAccount ! BankAccount.DepositCommand(100, probe.ref)
      probe.expectMessage(
        StatusReply.Success(
          BankAccount.ActionResponse("Amount 100 was deposited")
        )
      )
      bankAccount ! BankAccount.BalanceCheckCommand(probe.ref)
      probe.expectMessage(
        StatusReply.Success(BankAccount.BalanceCheckResponse(100))
      )
      bankAccount ! BankAccount.WithdrawCommand(120, probe.ref)
      probe.expectMessage(
        StatusReply.Error(
          "Account has insufficient funds. Available balance 100"
        )
      )
      bankAccount ! BankAccount.BalanceCheckCommand(probe.ref)
      probe.expectMessage(
        StatusReply.Success(BankAccount.BalanceCheckResponse(100))
      )
      testKit.stop(bankAccount)
    }
  }
}
