package com.baeldung.scala

object GenericsIntro {

  case class Apple(name: String)

  def main(args: Array[String]): Unit = {
    NonGenericWay.run()
  }

  object LongHandWay {

    case class AppleMagicHat(magic: Apple)

    case class RabbitMagicHat(magic: Rabbit)

    def run(): Unit = {
      val someHat = AppleMagicHat(Apple("gala"))
      val apple: Apple = someHat.magic
      println(apple.name)
    }
  }

  object NonGenericWay {

    case class MagicHat(magic: AnyRef)

    def run(): Unit = {
      val someHat = MagicHat(Rabbit(2))
      val apple: Apple = someHat.magic.asInstanceOf[Apple]
      println(apple.name)
    }
  }

  object GenericWay {

    case class MagicHat[A](magic: A)

    def run(): Unit = {
      val rabbitHat = MagicHat[Rabbit](Rabbit(2))
      val rabbit: Rabbit = rabbitHat.magic
      println(rabbit.cuteness)
    }
  }

  case class Rabbit(cuteness: Int)

  object GenericMethods {
    def middle[A](input: Seq[A]): A = input(input.size / 2)

    def itemsAt[A, B](index: Int, seq1: Seq[A], seq2: Seq[B]): (A, B) = (seq1(index), seq2(index))

    def run() = {

      val rabbits = List[Rabbit](Rabbit(2), Rabbit(3), Rabbit(7))
      val middleRabbit: Rabbit = middle[Rabbit](rabbits)

      val apples = List[Apple](Apple("gala"), Apple("pink lady"))
      val items: (Rabbit, Apple) = itemsAt[Rabbit, Apple](1, rabbits, apples)
    }

  }

  object NonGenericMethods {
    def totalSize(list1: List[_], list2: List[_]): Int = list1.length + list2.length
    def run() = {
      val rabbits = List[Rabbit](Rabbit(2), Rabbit(3), Rabbit(7))
      val strings = List("a", "b")
      val size: Int = totalSize(rabbits, strings)
    }

  }

}
