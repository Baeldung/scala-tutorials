package com.baeldung.scala.movingaverage

import scala.collection.immutable.Queue
import scala.io.Source

object OptimizedMovingAverage {
  def main(args: Array[String]): Unit = {
    val stream = getClass.getResourceAsStream("/GOOG.csv")
    val lines = Source.fromInputStream(stream).getLines().drop(1)
    val stockPrices = lines.map { line =>
      val Array(date, _, _, _, _, close, _) = line.split(",")
      StockPrice(date, close.toDouble)
    }.toList

    val windowSize = 60

    val optimizedSMA = stockPrices
      .foldLeft((Queue.empty[Double], List.empty[StockPrice], 0.0)) {
        case ((queue, averages, sum), StockPrice(date, price)) =>
          val newQueue = queue.enqueue(price)
          val newSum = sum + price

          if (newQueue.size > windowSize) {
            val (oldest, remainingQueue) = newQueue.dequeue
            val newAverage = (newSum - oldest) / windowSize
            (
              remainingQueue,
              StockPrice(date, newAverage) :: averages,
              newSum - oldest
            )
          } else if (newQueue.size == windowSize) {
            val newAverage = newSum / windowSize
            (newQueue, StockPrice(date, newAverage) :: averages, newSum)
          } else {
            (newQueue, averages, newSum)
          }
      }
      ._2
      .reverse

    println("Optimized 30-day Simple Moving Averages:")
    optimizedSMA.foreach { case StockPrice(date, average) =>
      println(s"$date: $average")
    }
  }
}
