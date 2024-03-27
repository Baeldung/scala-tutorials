package com.baeldung.scala.kafka.intro.producer.common

import com.sksamuel.avro4s._
import io.confluent.kafka.serializers.KafkaAvroSerializer
import org.apache.kafka.common.serialization.Serializer

import scala.reflect._

trait AvroSerializer {
  def AvroSerializer[T](implicit format: RecordFormat[T]): Serializer[T] =
    new Serializer[T] {
      val ser = new KafkaAvroSerializer()

      override def configure(
        configs: java.util.Map[String, _],
        isKey: Boolean
      ): Unit =
        ser.configure(configs, isKey)

      override def serialize(topic: String, data: T): Array[Byte] = Option(data)
        .map(data => ser.serialize(topic, format.to(data)))
        .getOrElse(Array.emptyByteArray)

      override def close(): Unit = ser.close()
    }
}
