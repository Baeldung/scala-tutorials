package com.baeldung.scala.zio.zio_kafka.workflow

import com.baeldung.scala.zio.zio_kafka.CustomMessage
import zio.ZIOAppDefault

object KafkaWorkflowApp extends ZIOAppDefault {

  private val BOOTSTRAP_SERVERS = List("localhost:9092")
  private val KAFKA_TOPIC = "baeldung"

  override def run =
    for {
      consumer <- KafkaConsumer
        .consume(BOOTSTRAP_SERVERS, "baeldung-consumer-group", KAFKA_TOPIC)
        .fork
      _ <- KafkaProducer
        .produce(KAFKA_TOPIC, "key", CustomMessage(1, "Hello", "Baeldung"))
        .provide(KafkaProducer.producerLayer(BOOTSTRAP_SERVERS))
        .repeatN(10)
      _ <- consumer.join
    } yield ()

}
