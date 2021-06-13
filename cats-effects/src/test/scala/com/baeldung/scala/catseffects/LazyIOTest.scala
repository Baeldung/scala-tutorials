package com.baeldung.scala.catseffects

import org.junit.Test

class LazyIOTest {
  
  @Test
  def twoTimesLaunch_test(): Unit = {
    val io = LazyIO.io(println("Launch missiles"))
    val twoRuns = for {
      one <- io
      two <- io
    } yield (one, two)
    
    twoRuns.runEffect()
  }
}
