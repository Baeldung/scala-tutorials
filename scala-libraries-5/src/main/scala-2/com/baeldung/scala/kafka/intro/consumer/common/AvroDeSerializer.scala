package com.baeldung.scala.kafka.intro.consumer.common

import com.sksamuel.avro4s._
import io.confluent.kafka.serializers.KafkaAvroDeserializer
import org.apache.avro.generic.IndexedRecord
import org.apache.kafka.common.serialization.Deserializer

trait AvroDeSerializer {
  def deserializer[T](implicit
    format: RecordFormat[T]
  ): Deserializer[T] = new Deserializer[T] {
    val deser = new KafkaAvroDeserializer()

    override def configure(
      configs: java.util.Map[String, _],
      isKey: Boolean
    ): Unit =
      deser.configure(configs, isKey)

    override def deserialize(topic: String, data: Array[Byte]): T = Option(data)
      .filter(_.nonEmpty)
      .map { data =>
        format
          .from(deser.deserialize(topic, data).asInstanceOf[IndexedRecord])
      }
      .getOrElse(null.asInstanceOf[T])

    override def close(): Unit = deser.close()
  }
}
