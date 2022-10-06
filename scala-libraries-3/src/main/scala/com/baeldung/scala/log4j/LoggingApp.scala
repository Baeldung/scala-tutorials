package com.baeldung.scala.log4j

import org.apache.logging.log4j.scala.Logging

import scala.util.{Failure, Success, Try}

object LoggingApp extends App with Logging {
  logger.info("Writing an informative message to the log")
  logger.debug("Writing a debug message to the log")
  Try(1 / 0) match {
    case Success(value)     => logger.warn("Math has changed")
    case Failure(exception) => logger.catching(exception)
  }
}
