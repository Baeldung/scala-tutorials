package com.baeldung.scala.scalatest.mockito

import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.mockito.MockitoSugar

import java.time.LocalDateTime
import scala.concurrent.Future

class InventoryServiceTest
  extends AnyWordSpec
  with MockitoSugar
  with ScalaFutures
  with Matchers {

  "Inventory Service" should {
    val startDate = LocalDateTime.parse("2022-01-03T10:15:30")

    "calculate the stock quantity" in {
      val txns = Seq(
        InventoryTransaction(
          1,
          "txn1",
          10,
          TxnTypes.IN,
          InventoryTypes.CHOCOLATE,
          startDate
        ),
        InventoryTransaction(
          2,
          "txn1",
          5,
          TxnTypes.OUT,
          InventoryTypes.CHOCOLATE,
          startDate.plusDays(1)
        ),
        InventoryTransaction(
          3,
          "txn2",
          2,
          TxnTypes.IN,
          InventoryTypes.CHOCOLATE,
          startDate.plusDays(2)
        ),
        InventoryTransaction(
          4,
          "txn3",
          15,
          TxnTypes.IN,
          InventoryTypes.SUGAR,
          startDate
        ),
        InventoryTransaction(
          4,
          "txn3",
          2,
          TxnTypes.IN,
          InventoryTypes.SALT,
          startDate
        )
      )
      val dao = mock[InventoryTransactionDao]
      val mockProducer = mock[KafkaProducer]
      val mockLogger = mock[Logger]
      val service = new InventoryService(dao, mockProducer, mockLogger)
      when(dao.getAll()).thenReturn(Future.successful(txns))
      val chocolateStock = service.getCurrentStock(InventoryTypes.CHOCOLATE)
      whenReady(chocolateStock) { stock =>
        stock shouldBe 7
      }
    }

    "save and publish to kafka" in {
      val dao = mock[InventoryTransactionDao]
      val txn = InventoryTransaction(
        1,
        "txn1",
        10,
        TxnTypes.IN,
        InventoryTypes.CHOCOLATE,
        startDate
      )
      val mockProducer = mock[KafkaProducer]
      val mockLogger = mock[Logger]
      val service = new InventoryService(dao, mockProducer, mockLogger)
      when(dao.saveAsync(any[InventoryTransaction]))
        .thenReturn(Future.successful(txn))
      doNothing().when(mockProducer).publish(txn)
      val result = service.saveAndPublish(txn)
      whenReady(result) { _ =>
        verify(mockProducer, times(1)).publish(any[InventoryTransaction])
      }
    }

    "save and NOT publish to kafka for non chocolate inventory" in {
      val dao = mock[InventoryTransactionDao]
      val txn = InventoryTransaction(
        1,
        "txn1",
        10,
        TxnTypes.IN,
        InventoryTypes.SALT,
        startDate
      )
      val mockProducer = mock[KafkaProducer]
      val mockLogger = mock[Logger]
      val service = new InventoryService(dao, mockProducer, mockLogger)
      when(dao.saveAsync(any[InventoryTransaction]))
        .thenReturn(Future.successful(txn))
      when(mockProducer.publish(any[InventoryTransaction]))
        .thenThrow(new RuntimeException("This should never occur"))
      val result = service.saveAndPublish(txn)
      whenReady(result) { _ =>
        verify(mockProducer, times(0)).publish(any[InventoryTransaction])
        verify(mockProducer, never).publish(any[InventoryTransaction])
      }

    }

    "save and log the txn details" in {
      val dao = mock[InventoryTransactionDao]
      val dt = startDate.plusMinutes(44)
      val txn = InventoryTransaction(
        100,
        "txn0199",
        10,
        TxnTypes.IN,
        InventoryTypes.SALT,
        dt
      )
      val mockProducer = mock[KafkaProducer]
      val mockLogger = mock[Logger]
      val service = new InventoryService(dao, mockProducer, mockLogger)
      when(dao.saveAsync(any[InventoryTransaction]))
        .thenReturn(Future.successful(txn))
      val result = service.saveAndLogTime(txn)
      whenReady(result) { _ =>
        val refCapture = ArgumentCaptor.forClass(classOf[String])
        val refLocalDateTime = ArgumentCaptor.forClass(classOf[LocalDateTime])

        verify(mockLogger, times(1))
          .logTime(refCapture.capture(), refLocalDateTime.capture())
        refCapture.getValue shouldBe txn.txnRef
        refLocalDateTime.getValue shouldBe txn.created
      }

    }

    "save only if authenticated" in {

      val txn = InventoryTransaction(
        1,
        "txn1",
        10,
        TxnTypes.IN,
        InventoryTypes.CHOCOLATE,
        startDate
      )
      val dao = spy(classOf[InventoryTransactionDaoImpl])
      val mockProducer = mock[KafkaProducer]
      val mockLogger = mock[Logger]
      val service = new InventoryService(dao, mockProducer, mockLogger)
      when(dao.saveAsync(txn)).thenReturn(Future.successful(txn))
      val result = service.saveWithAuth(txn, 100)
      whenReady(result) { res =>
        res.txnRef shouldBe txn.txnRef
      }
    }

    "fail if unauthenticated" in {

      val txn = InventoryTransaction(
        1,
        "txn1",
        10,
        TxnTypes.IN,
        InventoryTypes.CHOCOLATE,
        startDate
      )
      val dao = spy(classOf[InventoryTransactionDaoImpl])
      val mockProducer = mock[KafkaProducer]
      val mockLogger = mock[Logger]
      val service = new InventoryService(dao, mockProducer, mockLogger)
      when(dao.saveAsync(txn)).thenReturn(Future.successful(txn))
      val result = service.saveWithAuth(txn, 101)
      whenReady(result.failed) { res =>
        res.getMessage shouldBe "Unauthenticated access"
      }
    }

  }

}
