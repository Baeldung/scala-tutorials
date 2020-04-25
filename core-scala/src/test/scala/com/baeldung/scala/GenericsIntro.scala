package com.baeldung.scala

object GenericsIntro {

  case class Apple(name: String)

  def main(args: Array[String]): Unit = {
    NonGenericWay.run()
    GenericWay.run()
  }

  object NonGenericWay {

    case class MagicHat(magic: AnyRef)

    def run(): Unit = {
      val rabbitHat = MagicHat(Rabbit(2))
      val rabbit: Rabbit = rabbitHat.magic.asInstanceOf[Rabbit]
      println(rabbit.cuteness)
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

  def middle[A](input: Seq[A]): A = input(input.size / 2)

  def itemsAt[A, B](index: Int, seq1: Seq[A], seq2: Seq[B]): (A, B) = (seq1(index), seq2(index))

}