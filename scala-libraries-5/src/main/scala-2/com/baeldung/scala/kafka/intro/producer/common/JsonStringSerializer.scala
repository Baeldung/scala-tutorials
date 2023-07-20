package com.baeldung.scala.kafka.intro.producer.common

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.apache.kafka.common.serialization.StringSerializer

trait JsonStringSerializer[T] {

  implicit val keySerializer: StringSerializer = new StringSerializer()
  implicit val valueSerializer: StringSerializer = new StringSerializer()

  implicit val jsonMapper: JsonMapper = JsonMapper
    .builder()
    .addModule(DefaultScalaModule)
    .build()

  implicit class ValueOps(value: T) {
    def toJsonString()(implicit jsonMapper: JsonMapper): String = {
      jsonMapper.writeValueAsString(value)
    }
  }
}
