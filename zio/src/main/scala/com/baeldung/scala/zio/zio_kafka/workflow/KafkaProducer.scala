package com.baeldung.scala.zio.zio_kafka.workflow

import com.baeldung.scala.zio.zio_kafka.{CustomMessage, CustomMessageSerde}
import org.apache.kafka.clients.producer.RecordMetadata
import zio.{RIO, ZLayer}
import zio.kafka.producer.{Producer, ProducerSettings}

object KafkaProducer {
  def produce(
    topic: String,
    key: String,
    value: CustomMessage
  ): RIO[Any with Producer, RecordMetadata] =
    Producer.produce[Any, String, CustomMessage](
      topic = topic,
      key = key,
      value = value,
      keySerializer = CustomMessageSerde.key,
      valueSerializer = CustomMessageSerde.value
    )

  def producerLayer(
    bootstrapServers: List[String]
  ): ZLayer[Any, Throwable, Producer] =
    ZLayer.scoped(
      Producer.make(
        ProducerSettings().withBootstrapServers(bootstrapServers)
      )
    )
}
