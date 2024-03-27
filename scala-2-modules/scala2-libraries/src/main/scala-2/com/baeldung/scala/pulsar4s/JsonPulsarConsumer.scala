package com.baeldung.scala.pulsar4s

import com.sksamuel.pulsar4s.{
  Consumer,
  ConsumerConfig,
  ConsumerMessage,
  Subscription,
  Topic
}
import com.sksamuel.pulsar4s.jackson._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

class JsonPulsarConsumer(pulsarClient: PulsarClient)(implicit
  executionContext: ExecutionContext
) {

  val topic: Topic = Topic("pulsar4s-json-topic")
  val consumerConfig: ConsumerConfig =
    ConsumerConfig(Subscription.generate, Seq(topic))
  val consumer: Consumer[PulsarMessage] =
    pulsarClient.consumer[PulsarMessage](consumerConfig)

  def consume(): Try[ConsumerMessage[PulsarMessage]] =
    consumer.receive.map(message => {
      consumer.acknowledge(message.messageId)
      message
    })

  def consumeAsync(): Future[ConsumerMessage[PulsarMessage]] =
    consumer.receiveAsync.map(message => {
      consumer.acknowledge(message.messageId)
      message
    })
}
