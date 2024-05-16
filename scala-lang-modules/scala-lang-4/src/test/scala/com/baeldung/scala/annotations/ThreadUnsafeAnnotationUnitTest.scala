package com.baeldung.scala.annotations

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.util.concurrent.atomic.AtomicInteger
import scala.annotation.threadUnsafe

class ThreadUnsafeAnnotationUnitTest extends AnyFlatSpec with Matchers {

  it should "lock the entire class when lazy val is initialized" in {
    val lazyValClass = new LazyValClass()

    val thread1 = new Thread(new Runnable {
      def run() = {
        println("Thread 1 started, accessing lazy val")
        lazyValClass.syncLazyVal
      }
    })

    val thread2 = new Thread(new Runnable {
      def run() = {
        println("Thread 2 started, accessing lazy val")
        lazyValClass.syncLazyVal
      }
    })

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()
    lazyValClass.counter.get shouldBe 1 // since it locks, it will not evaluate again, so no increment
  }

  it should "NOT lock the entire class when @threadUnsafe annotation is used on lazy val" in {
    val unsafeLazyValClass = new UnsafeLazyValClass()

    val thread1 = new Thread(new Runnable {
      def run() = {
        println("Thread 1 started, accessing lazy val")
        unsafeLazyValClass.unsafeLazyVal
      }
    })

    val thread2 = new Thread(new Runnable {
      def run() = {
        println("Thread 2 started, accessing lazy val")
        unsafeLazyValClass.unsafeLazyVal
      }
    })

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()
    unsafeLazyValClass.counter.get shouldBe 2
    // since no synchronisation, it increments twice
  }

}

class LazyValClass {
  val counter = new AtomicInteger(0)
  lazy val syncLazyVal: String = {
    counter.incrementAndGet()
    println("Evaluating lazy val")
    Thread.sleep(3000)
    "scala"
  }
}

class UnsafeLazyValClass {
  val counter = new AtomicInteger(0)
  @threadUnsafe
  lazy val unsafeLazyVal: String = {
    counter.incrementAndGet()
    println("Evaluating unsafe lazy val")
    Thread.sleep(3000)
    "scala"
  }
}
