package com.baeldung.scala.structuraltypes

import scala.io.Source
import scala.language.reflectiveCalls

trait ResourceClosing {
  type Closable = { def close(): Unit }

  def using(resource: Closable)(fn: () => Unit) = {
    try {
      fn()
    } finally { resource.close() }
  }

  def using(file: Source)(fn: () => Unit) = {
    try {
      fn()
    } finally { file.close() }
  }
}
