package com.baeldung.scala.monad

import com.baeldung.scala.monad.LazyMonad.Lazy
import org.scalatest.flatspec.AnyFlatSpec

class LazyMonadUnitTest extends AnyFlatSpec {
  "Lazy.get" should "evaluate and return the internal value" in {
    val lazy42 = Lazy(42)
    assert( lazy42.get == 42)
  }

  "Lazy.flatMap" should "apply the given function to the internal value" in {
    val lazy42 = Lazy(42)
    val lazy43 = lazy42.flatMap(n => Lazy(n + 1))
    assert(lazy43.get ==  43)
  }

  "Lazy.map" should "apply the given function to the internal value" in {
    val lazy42 = Lazy(42)
    val lazy43 = lazy42.map(_ + 1)
    assert(lazy43.get ==  43)
  }

  "Flatten" should "remove one level of abstraction" in {
    val lazy42 = Lazy(42)
    val lazy43 = Lazy.flatten(lazy42.map(n => Lazy(n + 1)))
    assert(lazy43.get ==  43)
  }
}
