package com.baeldung.scala.config

import com.typesafe.config.{Config, ConfigFactory}
import scala.concurrent.duration._
import java.time
import com.typesafe.config.ConfigMemorySize
import com.typesafe.config.ConfigException

class ConfigUnitTest extends munit.FunSuite {

  test("Read configs with primitive types") {
    val config: Config = ConfigFactory.load()

    assertEquals(config.getInt("id"), 100)
    assertEquals(config.getString("name"), "baeldung")
    assertEquals(config.getDouble("price"), 2d)
    assertEquals(config.getBoolean("status"), false)
  }

  test("Read non basic datatypes successfully") {
    val config: Config = ConfigFactory.load()
    val duration: time.Duration = config.getDuration("complex-types.duration")
    val mem: ConfigMemorySize = config.getMemorySize("complex-types.heap-size")

    assertEquals(mem.toBytes, 1024L)
  }

  test("read a deeply nested structure") {
    val config: Config = ConfigFactory.load()
    val user = config.getString("app.database.postgres.username")
    assertEquals(user, "user")
    val dbConfig: Config = config.getConfig("app.database")
    assertEquals(dbConfig.getString("postgres.username"), "user")
    assertEquals(dbConfig.getString("postgres.url"), "localhost:5432")
  }

  test("substitute a configuration with another one") {
    val config: Config = ConfigFactory.load()
    val desc = config.getString("sub.desc")
    assertEquals(desc, "This is a baeldung project")
  }

  test("verify include directive") {
    val conf = ConfigFactory.load("main.conf")
    val username = conf.getString("db.username")
    val protocol = conf.getString("http.protocol")
    assertEquals(username, "postgres")
    assertEquals(protocol, "https")
  }

  test("use fallback to another file if not found in 1st file") {
    val defaultConf = ConfigFactory.load("application-default.conf")
    val conf = ConfigFactory.load("application.conf").withFallback(defaultConf)
    val version = conf.getString("fallback.version")
    assertEquals(version, "9.9")
    assert(
      ConfigFactory
        .load("application.conf")
        .hasPath("fallback.version") == false
    )
  }

  test("throw exception if key doesnt exist") {
    val conf = ConfigFactory.load()
    intercept[ConfigException](conf.getString("dummy.key"))
    assert(conf.hasPath("dummy.key") == false)
  }

}
