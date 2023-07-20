package com.baeldung.scala.kafka.intro.producer

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.apache.kafka.clients.producer.{
  Callback,
  KafkaProducer,
  ProducerRecord,
  RecordMetadata
}
import org.apache.logging.log4j.scala.Logging

trait ProducerUtils[T] extends Logging {

  implicit val jsonMapper: JsonMapper = JsonMapper
    .builder()
    .addModule(DefaultScalaModule)
    .build()

  implicit class ValueOps(value: T) {
    def toJsonString()(implicit jsonMapper: JsonMapper): String = {
      jsonMapper.writeValueAsString(value)
    }
  }

  implicit val callback: Callback = (
    metadata: RecordMetadata,
    exception: Exception
  ) =>
    Option(exception)
      .map(error => logger.error("fail to send record due to: ", error))
      .getOrElse(
        logger.info(
          s"Successfully produce a new record to kafka: ${s"topic: ${metadata.topic()}, partition: ${metadata
                .partition()}, offset: ${metadata.offset()}"}"
        )
      )

  def produce[K, V](
    producer: KafkaProducer[K, V],
    topic: String,
    key: K,
    value: V
  )(implicit callback: Callback): Unit = {
    val record = new ProducerRecord(topic, key, value)
    producer.send(record, callback)
  }

}
