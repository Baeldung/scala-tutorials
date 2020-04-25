package com.baeldung.scala.abstract_class_vs_trait

import com.baeldung.scala.abstract_class_vs_trait.AbstractClassWithTraits.{
  Actor,
  CommandHandler,
  Output,
  Person
}
import org.junit.Test
import org.scalatest.Matchers

class TypeParameterInAbstractClassAndTraitsUnitTest extends Matchers {

  @Test
  def givenTypeParameter_whenUsedInAbstractClass_thenShouldPatternMatchCorrectly() {

    trait MockOutput extends Output {
      var messages: Seq[String] = Seq()

      override def print(s: String) = messages = messages :+ s
    }

    class Person extends CommandHandler[Person.PersonCommands]

    val person = new Person() with MockOutput

    person ! Person.Hello()
    person ! "Hello!"

    person.messages foreach println

    person.messages should be(
      List("Received command | Hello()", "Received unexpected message | Hello!")
    )
  }

  @Test
  def givenTypeParameter_whenUsedInTraits_thenShouldNotPatternMatch() {

    trait MockOutput extends Output {
      var messages: Seq[String] = Seq()

      override def print(s: String) = messages = messages :+ s
    }

    trait CommandHandlerTrait[Command] extends Actor with Output {
      def receive: Receive = {
        case command: Command =>
          print(s"""
            Received command | $command 
            -- everything will be interpreted as Command 
            -- (thanks to type erasure)
            """)
        case other =>
          print(s"Received unexpected message | ${other}")
      }
    }

    class Person extends CommandHandlerTrait[Person.PersonCommands]

    val person = new Person() with MockOutput

    person ! Person.Hello()
    person ! "Hello!"

    person.messages foreach println

    person.messages should not be (
      List("Received command | Hello()", "Received unexpected message | Hello!")
    )
    person.messages forall (_ contains "Received command") should be(true)
  }
}
