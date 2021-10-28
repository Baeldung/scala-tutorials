package com.baeldung.scala.inlining

class InliningWarning {
  @inline
  def f = 10

  def callsite = f
}
