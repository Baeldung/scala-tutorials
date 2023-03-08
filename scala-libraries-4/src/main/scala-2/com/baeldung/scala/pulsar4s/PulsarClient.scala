package com.baeldung.scala.pulsar4s

import com.sksamuel.pulsar4s.{
  Consumer,
  ConsumerConfig,
  Producer,
  ProducerConfig,
  PulsarAsyncClient,
  PulsarClient,
  PulsarClientConfig
}
import org.apache.pulsar.client.api.Schema

class PulsarClient(pulsarServiceUrl: String) {

  private val config: PulsarClientConfig = PulsarClientConfig(pulsarServiceUrl)
  private val client: PulsarAsyncClient = PulsarClient(config)

  def producer[T: Schema](producerConfig: ProducerConfig): Producer[T] =
    client.producer[T](producerConfig)

  def consumer[T: Schema](consumerConfig: ConsumerConfig): Consumer[T] =
    client.consumer[T](consumerConfig)
}
