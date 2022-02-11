package com.baeldung.scala.scalatest.mockito

import java.time.LocalDateTime

object TxnTypes extends Enumeration {
  type TxnType = Value
  val IN, OUT = Value
}

object InventoryTypes extends Enumeration {
  type InventoryType = Value
  val CHOCOLATE, SUGAR, SALT = Value
}

case class InventoryTransaction(
  id: Long,
  txnRef: String,
  qty: Int,
  txnType: TxnTypes.TxnType,
  inventoryType: InventoryTypes.InventoryType,
  created: LocalDateTime
) {
  def getResolvedQuantity = if (txnType == TxnTypes.OUT) -qty else qty
}
