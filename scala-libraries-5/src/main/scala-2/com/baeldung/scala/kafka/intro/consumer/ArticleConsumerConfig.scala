package com.baeldung.scala.kafka.intro.consumer

import com.baeldung.scala.kafka.intro.ClientConfig
import com.typesafe.config.Config
import pureconfig.ConfigSource
import pureconfig.generic.auto.exportReader

import java.util

case class ArticleConsumerConfig(consumer: Config, topic: String)

object ArticleConsumerConfig extends ClientConfig {
  def getConfig(resource: String): (util.Map[String, AnyRef], String) = {
    val source =
      ConfigSource.resources(resource).loadOrThrow[ArticleConsumerConfig]
    val config = source.consumer.asJavaMap
    val topic = source.topic
    (config, topic)
  }
}
