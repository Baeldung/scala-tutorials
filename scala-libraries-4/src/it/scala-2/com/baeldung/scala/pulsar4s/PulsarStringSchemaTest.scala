package com.baeldung.scala.pulsar4s

import org.scalatest.{BeforeAndAfterAll, Ignore}
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AsyncWordSpec
import org.testcontainers.containers.PulsarContainer
import org.testcontainers.utility.DockerImageName

import scala.util.Success

@Ignore
class PulsarStringSchemaTest
  extends AsyncWordSpec
  with BeforeAndAfterAll
  with Matchers {

  val pulsar: PulsarContainer = new PulsarContainer(
    DockerImageName.parse("apachepulsar/pulsar:2.10.2")
  )

  override def beforeAll(): Unit = pulsar.start()

  override def afterAll(): Unit = pulsar.stop()

  "pulsar producer" should {
    "successfully send messages" in {
      val pulsarClient = new PulsarClient(pulsar.getPulsarBrokerUrl)
      val producer = new PulsarProducer(pulsarClient)
      val messageIdTry = producer.sendMessage("my-key", "a test message")
      messageIdTry mustBe a[Success[_]]
    }
  }

  "pulsar consumer" should {
    "successfully consume messages" in {
      val pulsarClient = new PulsarClient(pulsar.getPulsarBrokerUrl)
      val producer = new PulsarProducer(pulsarClient)
      val consumer = new PulsarConsumer(pulsarClient)
      val messageIdTry =
        producer.sendMessage("key-to-consume", "a test message")
      messageIdTry mustBe a[Success[_]]

      val messageTry = consumer.consume()
      messageTry mustBe a[Success[_]]
      messageTry.get.key must contain("key-to-consume")
      messageTry.get.value mustBe "a test message"
    }

    "successfully consume async messages" in {
      val pulsarClient = new PulsarClient(pulsar.getPulsarBrokerUrl)
      val producer = new PulsarProducer(pulsarClient)
      val consumer = new PulsarConsumer(pulsarClient)
      val messageIdTry =
        producer.sendMessage("key-to-consume", "a test message")
      messageIdTry mustBe a[Success[_]]

      consumer
        .consumeAsync()
        .map(message => {
          message.key must contain("key-to-consume")
          message.value mustBe "a test message"
        })
    }
  }
}
