package com.baeldung.scala.logging

import com.typesafe.scalalogging.{CanLog, LazyLogging, Logger, StrictLogging}
import org.slf4j.LoggerFactory

class ScalaLoggingSample {

  val loggerV1 = Logger("BaeldungLogger")
  val loggerV2 = Logger(getClass.getName)
  val loggerV3 = Logger(classOf[ScalaLoggingSample])
  val loggerV4 = Logger[ScalaLoggingSample]
  val loggerV5 = Logger(LoggerFactory.getLogger("FromSlf4jLogger"))

}
case class RequestId(id: String)

object LoggingImplicits {
  implicit case object WithRequestId extends CanLog[RequestId] {
    override def logMessage(originalMsg: String, a: RequestId): String =
      s"[REQ: ${a.id}] $originalMsg"
  }
}

object TestApp extends App {
  val sample = new ScalaLoggingSample
  sample.loggerV1.info("This is an info message")
  sample.loggerV2.error("This is an error message")
  sample.loggerV3.warn("This is a warning message")
  sample.loggerV4.debug("This is a debug message")
  sample.loggerV5.trace("This is a trace message")

  sample.loggerV5.whenDebugEnabled {
    println("This block is executed only if logger level is DEBUG")
  }

  def contextUsageMethod = {
    import LoggingImplicits._
    val ctxLogger = Logger.takingImplicit[RequestId]("ContextLogger")
    implicit val reqId = RequestId("user-1234")
    println()
    println()
    println()
    val message = "This is a message with user request context"
    ctxLogger.info(message)
  }

  contextUsageMethod

  val country = "India"
  val capital = "NewDelhi"
  sample.loggerV5.info(s"$capital is the capital of $country")
  val slf4jLogger = sample.loggerV5.underlying
  slf4jLogger.info("{} is the capital of {}", capital, country)
}

class LazyLoggingSample extends LazyLogging {
  logger.info("This is from lazy logging")
}
class StrictLoggingSample extends StrictLogging {
  logger.info("This is from strict logging")
}
