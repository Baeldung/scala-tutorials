package com.baeldung.scala.kafka.intro.consumer

import com.baeldung.scala.kafka.intro.common.Article
import com.baeldung.scala.kafka.intro.consumer.common.{
  AvroDeSerializer,
  ConsumerConfig,
  ConsumerUtils
}
import com.baeldung.scala.kafka.intro.producer.common.SerdeConfig.SerdeConfig
import com.sksamuel.avro4s.RecordFormat
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.{Deserializer, StringDeserializer}

import scala.concurrent.duration.{FiniteDuration, MILLISECONDS}
import scala.jdk.CollectionConverters._
import scala.jdk.javaapi.CollectionConverters.asJavaCollection
import scala.util.Try

object ArticleAvroConsumer
  extends App
  with ConsumerUtils[Article]
  with AvroDeSerializer {

  private val (config, topic) =
    ConsumerConfig.getConfig("kafka-intro-avro.conf")
  private val serde = SerdeConfig.getConfig("kafka-intro-avro.conf")

  val keyDeSerializer: StringDeserializer = new StringDeserializer()

  implicit lazy val Valueformat: RecordFormat[Article] = RecordFormat[Article]
  val valueDeSerializer: Deserializer[Article] = deserializer[Article]
  valueDeSerializer.configure(serde, false)

  private val consumer =
    new KafkaConsumer(config, keyDeSerializer, valueDeSerializer)

  consumer.subscribe(asJavaCollection(List(topic)))

  consumer.seekToBeginning(Nil.asJava)

  Try {
    while (true) {
      val messages = pool(consumer, FiniteDuration(1, MILLISECONDS))

      for ((_, article) <- messages) {
        logger.info(
          s"New article received. Title: ${article.title} .  Author: ${article.author.name}, Date: ${article.created}  "
        )
      }
    }
  }.recover { case error =>
    logger.error(error)
    logger.error(
      "Something went wrong when seeking messsages from begining. Unsubscribing"
    )
    consumer.unsubscribe();
  }
  consumer.close()
}
