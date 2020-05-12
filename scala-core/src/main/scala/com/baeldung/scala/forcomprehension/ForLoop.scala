package com.baeldung.scala.forcomprehension

trait ForLoop {
  // We are defining a println in a separate function in order to be able to test our iterations
  def sideEffectFunction(arg: Any) = println(arg)

  // Iterating an exclusive Range
  def iterateRangeTo (rangeTo: Range): Unit = {
    for (num <- rangeTo) {
      sideEffectFunction(num)
    }
  }

  // Iterating an inclusive Range
  def iterateRangeUntil (rangeUntil: Range): Unit = {
    for (num <- rangeUntil) {
      sideEffectFunction(num)
    }
  }

  // Iterating the Ranges as multiple generators
  def multipleGenerators (rangeTo: Range, rangeUntil: Range): Unit = {
    for {
      i <- rangeTo
      j <- rangeUntil
    } {
      sideEffectFunction(s"$i, $j")
    }
  }

  // Iterating a Sequence of Strings
  def iterateCollection (colors: Seq[String]): Unit = {
    for (color <- colors) {
      sideEffectFunction(color)
    }
  }

  // Iterating a Sequences of Strings, with multiple generators
  def iterateCollectionWithMultipleGenerators (colors: Seq[String]): Unit = {
    for (c1 <- colors; c2 <- colors; c3 <- colors) {
      sideEffectFunction(s"$c1$c2$c3 ")
    }
  }

  // Iterating a Sequences of Strings, with multiple generators and guards
  def iterateCollectionsWithGuards(colors: Seq[String]): Unit = {
    for {
      c1 <- colors
      c2 <- colors
      if c2 != c1
      c3 <- colors
      if c3 != c2 && c3 != c1
    } {
      sideEffectFunction(s"$c1$c2$c3 ")
    }
  }

  // Iterating a Map of String to Strings
  def iterateMap  (map: Map[String, String]): Unit = {
    for ((key,value) <- map) {
      sideEffectFunction(s"""$key is for $value""")
    }
  }

  // Iterating a Map of String to List of Strings
  def iterateMapMultipleGenerators (deck: Map[String, List[String]]): Unit = {
    for {
      (suit, cards) <- deck
      card <- cards
    } {
      sideEffectFunction(s"""$card of $suit""")
    }
  }

  // Pure List iteration
  def pureIteration (numbers: List[Int]): List[String] = {
    for (number <- numbers) yield {
      s"""$number + $number = ${number + number}"""
    }
  }

  // Pure multiple Optional with for-comprehension
  def forComprehensionWithOptionals (someIntValue: Option[Int], someStringValue: Option[String]): Option[String] = {
    for {
      intValue <- someIntValue
      stringValue <- someStringValue
    } yield {
      s"""$intValue is $stringValue"""
    }
  }

  // Pure multiple Optional with map/flatMap
  def mapOptionals (someIntValue: Option[Int], someStringValue: Option[String]): Option[String] = {
    someIntValue.flatMap(intValue => someStringValue.map(stringValue => s"""$intValue is $stringValue"""))
  }
}
