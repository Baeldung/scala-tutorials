package com.baeldung.scala.scalatest.mockito

import scala.concurrent.Future

trait InventoryTransactionDao {
  def saveAsync(txn: InventoryTransaction): Future[InventoryTransaction]

  def getAll(): Future[Seq[InventoryTransaction]]

  def authenticated(userId: Long): Boolean
}

class InventoryTransactionDaoImpl extends InventoryTransactionDao {
  def saveAsync(txn: InventoryTransaction): Future[InventoryTransaction] =
    Future.failed(new Exception("Async not implemented"))

  def getAll(): Future[Seq[InventoryTransaction]] =
    Future.failed(new Exception("Not implemented"))

  def authenticated(userId: Long): Boolean = {
    userId == 100
  }
}
