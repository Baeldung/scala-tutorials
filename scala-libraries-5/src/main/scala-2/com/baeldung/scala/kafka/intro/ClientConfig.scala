package com.baeldung.scala.kafka.intro

import com.typesafe.config.Config

import java.util.Properties
import scala.jdk.CollectionConverters._
trait ClientConfig {
  implicit class configMapperOps(config: Config) {
    def asJavaMap: java.util.Map[String, AnyRef] = config.toMap.asJava

    def toMap: Map[String, AnyRef] = config
      .entrySet()
      .asScala
      .map(pair => (pair.getKey, config.getAnyRef(pair.getKey)))
      .toMap

    def toProperties: Properties = {
      val properties = new Properties()
      properties.putAll(config.toMap.asJava)
      properties
    }
  }
}
