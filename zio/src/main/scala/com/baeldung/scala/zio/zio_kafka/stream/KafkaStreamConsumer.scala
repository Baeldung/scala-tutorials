package com.baeldung.scala.zio.zio_kafka.stream

import zio.ZLayer
import zio.kafka.consumer.{Consumer, ConsumerSettings}

object KafkaStreamConsumer {

  def consumerLayer(
    bootstrapServers: List[String],
    groupId: String
  ): ZLayer[Any, Throwable, Consumer] =
    ZLayer.scoped(
      Consumer.make(
        ConsumerSettings(bootstrapServers)
          .withGroupId(groupId)
      )
    )

}
