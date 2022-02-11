package com.baeldung.scala.scalatest.mockito

import java.time.LocalDateTime
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Random

trait KafkaProducer {
  def publish(txn: InventoryTransaction): Unit
}

trait Logger {
  def logTime(txnRef: String, dateTime: LocalDateTime): Unit
}

class InventoryService(
  dao: InventoryTransactionDao,
  kafkaProducer: KafkaProducer,
  logger: Logger
) {

  def getCurrentStock(
    inventoryType: InventoryTypes.InventoryType
  ): Future[Int] = {
    val inventoryTxns = dao.getAll()
    inventoryTxns.map { inventories =>
      inventories
        .filter(_.inventoryType == inventoryType)
        .foldLeft[Int](0)((acc, inv) => acc + inv.getResolvedQuantity)
    }
  }

  def saveAndPublish(
    txn: InventoryTransaction
  ): Future[InventoryTransaction] = {
    dao.saveAsync(txn).map { res =>
      if (txn.inventoryType == InventoryTypes.CHOCOLATE)
        kafkaProducer.publish(txn)
      res
    }
  }

  def saveAndLogTime(
    txn: InventoryTransaction
  ): Future[InventoryTransaction] = {
    dao.saveAsync(txn).map { res =>
      logger.logTime(res.txnRef, res.created)
      res
    }
  }

  def calculateStockValue(
    inventoryType: InventoryTypes.InventoryType
  ): Future[Double] = {
    for {
      stock <- getCurrentStock(inventoryType)
      unitPrice = PriceService.getPrice(inventoryType)
    } yield stock * unitPrice
  }

  def saveWithAuth(
    inventoryTransaction: InventoryTransaction,
    userId: Long
  ): Future[InventoryTransaction] = {
    if (dao.authenticated(userId))
      dao.saveAsync(inventoryTransaction)
    else
      Future.failed(new Exception("Unauthenticated access"))
  }

}

object PriceService {
  def getPrice(inventoryType: InventoryTypes.InventoryType): Double =
    Random.nextInt(100).toDouble
}
