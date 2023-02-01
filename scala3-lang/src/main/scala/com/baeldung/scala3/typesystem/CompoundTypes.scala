package com.baeldung.scala3.typesystem

object CompoundTypes {
  object Union {
    def parse(input: String): Int | String =
      try input.toInt
      catch case _ => "Not a number"
  }

  object Intersection {
    trait Show:
      def show: String

    trait Closable:
      def close: Unit

    type Resource = Show & Closable

    def shutdown(resource: Resource) =
      println(s"Closing resource ${resource.show}")
      resource.close

    object res extends Show, Closable:
      override def show = "resource#1"

      override def close = println("Resource closed!")
  }
}
