package com.baeldung.scala.pulsar4s

import com.sksamuel.pulsar4s.{
  DefaultProducerMessage,
  MessageId,
  Producer,
  ProducerConfig,
  Topic
}
import org.apache.pulsar.client.api.Schema

import scala.util.Try

class PulsarProducer(pulsarClient: PulsarClient) {

  implicit val schema: Schema[String] = Schema.STRING

  val topic: Topic = Topic("pulsar4s-topic")
  val producerConfig: ProducerConfig = ProducerConfig(topic)
  val producer: Producer[String] = pulsarClient.producer[String](producerConfig)

  def sendMessage(key: String, message: String): Try[MessageId] =
    producer.send(
      DefaultProducerMessage[String](Some(key), message)
    )
}
