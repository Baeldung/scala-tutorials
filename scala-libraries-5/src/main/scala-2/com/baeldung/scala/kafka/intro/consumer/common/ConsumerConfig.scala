package com.baeldung.scala.kafka.intro.consumer.common

import com.baeldung.scala.kafka.intro.common.ClientConfig
import com.typesafe.config.Config
import pureconfig.ConfigSource
import pureconfig.generic.auto.exportReader

import java.util

case class ConsumerConfig(consumer: Config, topic: String)

object ConsumerConfig extends ClientConfig {
  def getConfig(resource: String): (util.Map[String, AnyRef], String) = {
    val source =
      ConfigSource.resources(resource).loadOrThrow[ConsumerConfig]
    val config = source.consumer.asJavaMap
    val topic = source.topic
    (config, topic)
  }
}
