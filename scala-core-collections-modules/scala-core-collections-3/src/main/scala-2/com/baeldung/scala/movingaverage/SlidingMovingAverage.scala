package com.baeldung.scala.movingaverage

case class StockPrice(date: String, price: Double)

object SlidingMovingAverage extends App {

  // Sample stock prices
  val stockPrices = List(
    StockPrice("2021-01-01", 1.2),
    StockPrice("2021-01-02", 1.3),
    StockPrice("2021-01-03", 1.4),
    StockPrice("2021-01-04", 1.5),
    StockPrice("2021-01-05", 1.6)
  )

  val windowSize = 3

  // Simple Moving Average (SMA)
  val sma = stockPrices
    .sliding(windowSize)
    .map { window =>
      val average = window.map(_.price).sum / window.size
      val lastDate = window.last.date
      StockPrice(lastDate, average)
    }
    .toList

  // Weighted Moving Average (WMA)
  val weights = List(0.1, 0.2, 0.7)
  val wma = stockPrices
    .sliding(windowSize)
    .map { window =>
      val weightedSum = (window.map(_.price) zip weights).map {
        case (price, weight) =>
          price * weight
      }.sum
      val lastDate = window.last.date
      StockPrice(lastDate, weightedSum)
    }
    .toList

  // Output the results
  println("Simple Moving Average:")
  sma.foreach(println)

  println("\nWeighted Moving Average:")
  wma.foreach(println)
}
