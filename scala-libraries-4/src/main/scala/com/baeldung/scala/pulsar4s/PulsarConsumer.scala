package com.baeldung.scala.pulsar4s

import com.sksamuel.pulsar4s.{
  Consumer,
  ConsumerConfig,
  ConsumerMessage,
  Subscription,
  Topic
}
import org.apache.pulsar.client.api.Schema

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

class PulsarConsumer(pulsarClient: PulsarClient)(implicit
  executionContext: ExecutionContext
) {

  implicit val schema: Schema[String] = Schema.STRING

  val topic: Topic = Topic("pulsar4s-topic")
  val consumerConfig: ConsumerConfig =
    ConsumerConfig(Subscription.generate, Seq(topic))
  val consumer: Consumer[String] = pulsarClient.consumer[String](consumerConfig)

  def consume(): Try[ConsumerMessage[String]] =
    consumer.receive.map(message => {
      consumer.acknowledge(message.messageId)
      message
    })

  def consumeAsync(): Future[ConsumerMessage[String]] =
    consumer.receiveAsync.map(message => {
      consumer.acknowledge(message.messageId)
      message
    })
}
