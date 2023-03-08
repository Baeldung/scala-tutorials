package com.baeldung.scala

import java.util.concurrent.TimeUnit
import org.openjdk.jmh.annotations._

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Array(Mode.All))
class Benchmark_Specialized {

  @Benchmark
  def testNotSpecialized: Unit = {
    val notSpecialized = new NotSpecialized[Int]
    (0 to 1000000).map(notSpecialized.get(_))
  }

  @Benchmark
  def testOnlyTwoTypesSpecialized: Unit = {
    val onlyTwoTypesSpecialized = new OnlyTwoTypesSpecialized[Int]
    (0 to 1000000).map(onlyTwoTypesSpecialized.get(_))
  }

  @Benchmark
  def testAllTypesSpecialized: Unit = {
    val allSpecialized = new AllTypesSpecialized[Int]
    (0 to 1000000).map(allSpecialized.get(_))
  }

}

class AllTypesSpecialized[@specialized T] {
  def get(value: T): T = value
}

class OnlyTwoTypesSpecialized[@specialized(Int, Boolean) T] {
  def get(value: T): T = value
}

class NotSpecialized[T] {
  def get(value: T): T = value
}
