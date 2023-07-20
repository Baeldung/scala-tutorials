package com.baeldung.scala.kafka.intro.producer

import com.baeldung.scala.kafka.intro.common.Article
import com.baeldung.scala.kafka.intro.producer.common.{
  AvroSerializer,
  ProducerConfig,
  ProducerUtils
}
import com.sksamuel.avro4s.RecordFormat
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.common.serialization.{Serializer, StringSerializer}

object ArticleAvroProducer
  extends App
  with ProducerUtils[Article]
  with AvroSerializer {

  private val (config, topic) =
    ProducerConfig.getConfig("kafka-intro-avro.conf")

  val keySerializer: StringSerializer = new StringSerializer()

  implicit lazy val Valueformat: RecordFormat[Article] = RecordFormat[Article]
  val valueSerializer: Serializer[Article] = AvroSerializer[Article]
  valueSerializer.configure(config, false)

  private val producer =
    new KafkaProducer(config, keySerializer, valueSerializer)
  private val articles = Generator.articles
  for (article <- articles) {
    produce(producer, topic, article.id, article)
  }
  producer.close()

}
