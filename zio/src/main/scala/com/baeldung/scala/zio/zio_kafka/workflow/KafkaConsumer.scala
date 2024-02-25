package com.baeldung.scala.zio.zio_kafka.workflow

import com.baeldung.scala.zio.zio_kafka.CustomMessageSerde
import zio._
import zio.kafka.consumer.{Consumer, ConsumerSettings, Subscription}

object KafkaConsumer {
  def consume(
    bootstrapServers: List[String],
    groupId: String,
    topic: String
  ): RIO[Any, Unit] =
    Consumer.consumeWith(
      settings = ConsumerSettings(bootstrapServers)
        .withGroupId(groupId),
      subscription = Subscription.topics(topic),
      keyDeserializer = CustomMessageSerde.key,
      valueDeserializer = CustomMessageSerde.value
    )(record =>
      Console
        .printLine(
          s"Consumed message with key: ${record.key()} and value: ${record.value()}"
        )
        .orDie
    )
}
