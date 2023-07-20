package com.baeldung.scala.kafka.intro.producer

import com.baeldung.scala.kafka.intro._
import com.baeldung.scala.kafka.intro.common.{Article, Author}

import java.util.{Date, UUID}

object Generator {
  def articles: List[Article] = {
    List(
      Article(
        UUID.randomUUID.toString,
        "Introduction to Scala Programming",
        "Scala is a powerful programming language...",
        new Date(),
        Author(1, "John Doe")
      ),
      Article(
        UUID.randomUUID.toString,
        "Introduction to Scala Spire",
        "Spire  is a powerful numerical library...",
        new Date(),
        Author(2, "Jane Doe")
      ),
      Article(
        UUID.randomUUID.toString,
        "Introduction to Kafka",
        "In this article, we'll have an overview of kafka in scala...",
        new Date(),
        Author(3, "Foo Bar")
      )
    )
  }
}
