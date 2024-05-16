package com.baeldung.pureconfig

import com.typesafe.config.ConfigFactory.parseString
import pureconfig.ConfigSource
import pureconfig._
import pureconfig.generic.auto._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import impl._
import pureconfig.module.enumeratum._
import pureconfig.generic.ProductHint
import pureconfig.error.ConfigReaderException
import scala.concurrent.duration._

class SampleConfigLoaderUnitTest extends AnyWordSpec with Matchers {

  "pureconfig" should {
    "load config successfully" in {
      val kafkaConf = ConfigSource.default.at("kafka").load[KafkaConfig]
      val graphiteConf = ConfigSource.default.at("graphite").load[GraphiteConf]
      kafkaConf.isRight shouldBe true
      graphiteConf.isRight shouldBe true
      graphiteConf.right.get.enabled shouldBe true
      kafkaConf.right.get.protocol shouldBe Protocol.Https
      kafkaConf.right.get.timeout shouldBe 2.seconds
    }

    "load a config file other than application conf" in {
      val notificationConf =
        ConfigSource.resources("notification.conf").load[NotificationConfig]
      notificationConf.isRight shouldBe true
      notificationConf.right.get.params shouldBe "status=completed"
      notificationConf.right.get.fullURL shouldBe "http://mynotificationservice.com/push?status=completed"
    }

    "load a config from string content" in {
      val strConf = ConfigSource
        .string(
          """{"notification-url": "https://newURL", "params":"status=pending"}"""
        )
        .load[NotificationConfig]
      strConf.isRight shouldBe true
      strConf.right.get.params shouldBe "status=pending"
    }

    "fail to load config if any field is not proper" in {
      val strConf = ConfigSource
        .string(
          """{"notification-u": "https://wrongURL", "params":"status=completed"}"""
        )
        .load[NotificationConfig]
      strConf.isRight shouldBe false
    }

    "throw an exception if loadOrThrow is used" in {
      assertThrows[ConfigReaderException[_]] {
        ConfigSource
          .string(
            """{"notification-u": "https://wrongURL", "params":"status=completed"}"""
          )
          .loadOrThrow[NotificationConfig]
      }
    }
  }
}
