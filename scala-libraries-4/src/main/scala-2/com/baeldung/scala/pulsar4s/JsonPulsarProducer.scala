package com.baeldung.scala.pulsar4s

import com.sksamuel.pulsar4s.{
  DefaultProducerMessage,
  MessageId,
  Producer,
  ProducerConfig,
  Topic
}
import com.sksamuel.pulsar4s.jackson._

import scala.util.Try

class JsonPulsarProducer(pulsarClient: PulsarClient) {

  val topic: Topic = Topic("pulsar4s-json-topic")
  val producerConfig: ProducerConfig = ProducerConfig(topic)
  val producer: Producer[PulsarMessage] =
    pulsarClient.producer[PulsarMessage](producerConfig)

  def sendMessage(key: String, message: PulsarMessage): Try[MessageId] =
    producer.send(DefaultProducerMessage[PulsarMessage](Some(key), message))

}
