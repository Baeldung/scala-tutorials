package com.baeldung.scala.lambdas

object IntTransformer {
  def transform[A](n: Int)(fun: Int => A): A = fun(n)
}
