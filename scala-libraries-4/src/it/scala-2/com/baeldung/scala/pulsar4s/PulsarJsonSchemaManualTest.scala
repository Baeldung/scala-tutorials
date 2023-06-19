package com.baeldung.scala.pulsar4s

import org.scalatest.{BeforeAndAfterAll, Ignore}
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AsyncWordSpec
import org.testcontainers.containers.PulsarContainer
import org.testcontainers.utility.DockerImageName

import scala.util.Success

@Ignore
//ignored since this needs docker environment, which is not available in jenkins
class PulsarJsonSchemaManualTest
  extends AsyncWordSpec
  with BeforeAndAfterAll
  with Matchers {

  val pulsar: PulsarContainer = new PulsarContainer(
    DockerImageName.parse("apachepulsar/pulsar:2.10.2")
  )

  override def beforeAll(): Unit = pulsar.start()

  override def afterAll(): Unit = pulsar.stop()

  "pulsar json producer" should {
    "successfully send messages" in {
      val pulsarClient = new PulsarClient(pulsar.getPulsarBrokerUrl)
      val producer = new JsonPulsarProducer(pulsarClient)
      val messageIdTry = producer.sendMessage(
        "my-key",
        PulsarMessage(1, "a test message", System.currentTimeMillis())
      )
      messageIdTry mustBe a[Success[_]]
    }
  }

  "pulsar consumer" should {
    "successfully consume messages" in {
      val pulsarClient = new PulsarClient(pulsar.getPulsarBrokerUrl)
      val producer = new JsonPulsarProducer(pulsarClient)
      val consumer = new JsonPulsarConsumer(pulsarClient)
      val messageIdTry =
        producer.sendMessage(
          "key-to-consume",
          PulsarMessage(2, "a test message", System.currentTimeMillis())
        )
      messageIdTry mustBe a[Success[_]]

      val messageTry = consumer.consume()
      messageTry mustBe a[Success[_]]
      messageTry.get.key must contain("key-to-consume")
      messageTry.get.value.id mustBe 2
      messageTry.get.value.message mustBe "a test message"
    }

    "successfully consume async messages" in {
      val pulsarClient = new PulsarClient(pulsar.getPulsarBrokerUrl)
      val producer = new JsonPulsarProducer(pulsarClient)
      val consumer = new JsonPulsarConsumer(pulsarClient)
      val messageIdTry =
        producer.sendMessage(
          "key-to-consume",
          PulsarMessage(3, "a test message", System.currentTimeMillis())
        )
      messageIdTry mustBe a[Success[_]]

      consumer
        .consumeAsync()
        .map(message => {
          message.key must contain("key-to-consume")
          message.value.id mustBe 3
          message.value.message mustBe "a test message"
        })
    }
  }
}
