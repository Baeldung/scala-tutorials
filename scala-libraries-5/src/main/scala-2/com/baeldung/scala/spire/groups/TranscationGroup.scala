package com.baeldung.scala.spire.groups

import spire.algebra._
import spire.implicits._

case class Transaction(amount: Int)

object TransactionGroup extends Group[Transaction] {
  def combine(x: Transaction, y: Transaction): Transaction = Transaction(
    x.amount + y.amount
  )
  def empty: Transaction = Transaction(0)
  def inverse(a: Transaction): Transaction = Transaction(-a.amount)
}
