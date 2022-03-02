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

class SampleConfigLoader extends AnyWordSpec with Matchers {

  "pureconfig" should {
    "load config successfully" in {
      val httpConf = ConfigSource.default.at("http").load[HttpConfig]
      val monitoringConf = ConfigSource.default.at("monitoring").load[MonitoringConf]
      httpConf.isRight shouldBe true
      monitoringConf.isRight shouldBe true
    }

    "load a config file other than application conf" in {
      val dbConf = ConfigSource.resources("database.conf").load[DatabaseConfig]
      dbConf.isRight shouldBe true
      dbConf.right.get.databaseName shouldBe "configs"
    }

    "load a config from string content" in {
      val strConf = ConfigSource
        .string("""{"database-name": "strDB", "url":"mysql://localhost"}""")
        .load[DatabaseConfig]
      strConf.isRight shouldBe true
      strConf.right.get.databaseName shouldBe "strDB"
    }

    "fail to load config if any field is not proper" in {
      val strConf = ConfigSource
        .string("""{"database-nam": "strDB", "url":"mysql://localhost"}""")
        .load[DatabaseConfig]
      strConf.isRight shouldBe false
    }

    "throw an exception if loadOrThrow is used" in {
      assertThrows[ConfigReaderException[_]] {
        ConfigSource
          .string("""{"database-nam": "strDB", "url":"mysql://localhost"}""")
          .loadOrThrow[DatabaseConfig]
      }
    }
  }
}
