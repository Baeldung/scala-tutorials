package com.baeldung.scala.abstractclassesvstraits

object MarkTwainStatsComputeMain {

  def main(args: Array[String]): Unit = {

    val books = List(
      "https://www.gutenberg.org/files/70/70-0.txt",
      "https://www.gutenberg.org/files/3186/3186-0.txt"
    )

    val statistics: List[Double] = books.map { url =>
      val source = scala.io.Source.fromURL(url)
      val content: List[String] = source.getLines().flatMap(_.split("\\s+")).toList
      // using the URL as identifier
      new TextMeanLengthComputation(url,content).compute
    }

    statistics foreach println
  }
}
