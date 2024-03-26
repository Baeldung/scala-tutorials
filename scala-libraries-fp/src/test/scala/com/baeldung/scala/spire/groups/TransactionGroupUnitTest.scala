package com.baeldung.scala.spire.groups

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import spire.implicits._
import spire.math._

class TransactionGroupUnitTest extends AnyWordSpec with Matchers {

  "TransactionGroup" should {
    val deposit = Transaction(1250)
    val withdrawal = Transaction(-450)
    val reversal = TransactionGroup.inverse(withdrawal)

    "calculate the account balance" in {
      val balance = TransactionGroup.combine(deposit, withdrawal)
      assert(balance.amount == 800)
    }

    "calculate original transaction deposit" in {
      val balance = TransactionGroup.combine(deposit, withdrawal)
      val depositOriginal = TransactionGroup.combine(balance, reversal)
      assert(depositOriginal.amount == deposit.amount)
    }
  }
}
