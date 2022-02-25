package com.baeldung.pureconfig

import enumeratum._
import pureconfig._
import pureconfig.configurable._
import pureconfig.generic.auto._
import pureconfig.generic.semiauto._

import java.time.{LocalDate, LocalDateTime}
import java.time.format.DateTimeFormatter
import scala.concurrent.duration.FiniteDuration
import pureconfig.module.enumeratum._

sealed trait Protocol
object Protocol {
  implicit val protocolConvert: ConfigReader[Protocol] = deriveEnumerationReader[Protocol]
  case object Http extends Protocol
  case object Https extends Protocol
}

object impl {
  implicit val localDateConvert = localDateConfigConvert(DateTimeFormatter.ISO_DATE)
  implicit val localDateTimeConvert = localDateTimeConfigConvert(DateTimeFormatter.ISO_DATE_TIME)
}

final case class Port(number: Int) extends AnyVal

final case class HttpConfig(
  host: String,
  port: Port,
  protocol: Protocol,
  defaultTimeout: FiniteDuration
)

final case class MonitoringServer(host: String, port: Port)

final case class MonitoringConf(
  enabled: Boolean,
  servers: Seq[MonitoringServer]
)

/***  Config using Enumeratum based fields ***/

sealed trait Env extends EnumEntry

object Env extends Enum[Env] {
  case object Prod extends Env
  case object Test extends Env

  override val values = findValues
}

final case class BaseAppConfig(name: String, startDate: LocalDate, env: Env)


import pureconfig._
import pureconfig.generic.auto._
import pureconfig.module.enumeratum._
import com.typesafe.config.ConfigFactory.parseString
import enumeratum._
import enumeratum.EnumEntry._

sealed trait Greeting extends EnumEntry with Snakecase

object Greeting extends Enum[Greeting] {
  val values = findValues
  case object Hello extends Greeting
  case object GoodBye extends Greeting
  case object ShoutGoodBye extends Greeting with Uppercase
}

case class GreetingConf(s: Greeting, e: Greeting)

case class DatabaseConfig(url:String, databaseName:String)
