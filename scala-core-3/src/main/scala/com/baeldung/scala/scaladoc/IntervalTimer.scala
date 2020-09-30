package com.baeldung.scala.scaladoc

/** Represents a timer with interval.
  *
  * Specify how many `reps` desired for the timer and the `interval` between `reps`
  *
  * @constructor Create a timer with a specified `reps` and `interval`
  * @param reps Number of repetitions the timer will run.
  * @param interval Time between repetitions, in seconds. The default is 30 seconds.
  */
class IntervalTimer(val reps: Int, val interval: Int = 30) {

  /** Start this timer based on defined `reps` and `interval`.
    *
    * Print one message every second and another when each repetition is completed.
    * It cannot be stopped.
    */
  def start(): Unit = {
    Array.range(1, reps + 1).foreach { rep =>
      Array.range(1, interval + 1).foreach { second =>
        Thread.sleep(1000)
        println(s"tic toc $second")
      }
      println(s"rep $rep is finished.")
    }
  }

  /** Get total time, in seconds, that will be counted for this timer.
    *
    * @return The total number of seconds elapsed for this timer.
    */
  def getTotalSeconds: Int = {
    interval * reps
  }

  /** Markup examples for scaladoc
    *
    * =Heading=
    * ==Sub-Heading==
    * `monospace`
    * ''italic text''
    * '''bold text'''
    * __underline__
    * ^superscript^
    * ,,subscript,,
    * [[entity link]], e.g. [[scala.collection.Seq]]
    * [[https://external.link External Link]],
    *   e.g. [[https://scala-lang.org Scala Language Site]]
    *
    * {{{
    * Code block
    * val example = 1
    * }}}
    *
    * Here is an unordered list:
    *
    *  - First item
    *  - Second item
    *    - Sub-item to the second
    *    - Another sub-item
    *  - Third item
    *
    * Here is an ordered list:
    *
    *  1. First numbered item
    *  1. Second numbered item
    *    i. Sub-item to the second
    *    i. Another sub-item
    *  1. Third item
    */
  def markupExample(): Unit = { }
}


