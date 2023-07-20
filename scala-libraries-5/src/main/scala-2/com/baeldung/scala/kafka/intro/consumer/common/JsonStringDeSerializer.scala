package com.baeldung.scala.kafka.intro.consumer.common

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.apache.kafka.common.serialization.StringDeserializer

import scala.reflect._

trait JsonStringDeSerializer[T] {

  implicit val keyDeSerializer: StringDeserializer = new StringDeserializer()
  implicit val valueDeSerializer: StringDeserializer = new StringDeserializer()

  implicit val jsonMapper: JsonMapper = JsonMapper
    .builder()
    .addModule(DefaultScalaModule)
    .enable(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES)
    .build()

  def fromJsonString(
    str: String
  )(implicit jsonMapper: JsonMapper, classTag: ClassTag[T]): T = {
    jsonMapper.readValue(str, classTag.runtimeClass).asInstanceOf[T]
  }

}
