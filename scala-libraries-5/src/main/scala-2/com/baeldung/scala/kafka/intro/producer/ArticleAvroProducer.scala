package com.baeldung.scala.kafka.intro.producer

import com.baeldung.scala.kafka.intro.common.{Article, SerdeConfig}
import com.baeldung.scala.kafka.intro.producer.common.{
  AvroSerializer,
  ProducerConfig,
  ProducerUtils
}
import com.sksamuel.avro4s.RecordFormat
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.common.serialization.{Serializer, StringSerializer}

import scala.util.Try

object ArticleAvroProducer
  extends App
  with ProducerUtils[Article]
  with AvroSerializer {

  private val (config, topic) =
    ProducerConfig.getConfig("kafka-intro-avro.conf")
  private val serde = SerdeConfig.getConfig("kafka-intro-avro.conf")

  val keySerializer: StringSerializer = new StringSerializer()

  implicit lazy val Valueformat: RecordFormat[Article] = RecordFormat[Article]
  val valueSerializer: Serializer[Article] = AvroSerializer[Article]
  valueSerializer.configure(serde, false)

  private val producer =
    new KafkaProducer(config, keySerializer, valueSerializer)
  private val articles = Generator.articles

  Try {
    producer.initTransactions()
    producer.beginTransaction()
    for (article <- articles) {
      produce(producer, topic, article.id, article)
    }
    producer.commitTransaction()
    logger.info("Successfully completed kafka transaction.")
  }.recover { case error =>
    logger.error(error)
    logger.error(
      "Something went wrong during kafka transcation processing. Aborting"
    )
    producer.abortTransaction();
  }
  producer.close()

}
