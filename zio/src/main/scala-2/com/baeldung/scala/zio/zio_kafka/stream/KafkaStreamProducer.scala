package com.baeldung.scala.zio.zio_kafka.stream


import zio.ZLayer
import zio.kafka.producer.{Producer, ProducerSettings}

object KafkaStreamProducer {

  def producerLayer(
                     bootstrapServers: List[String]
                   ): ZLayer[Any, Throwable, Producer] =
    ZLayer.scoped(
      Producer.make(
        ProducerSettings(bootstrapServers)
      )
    )
}
