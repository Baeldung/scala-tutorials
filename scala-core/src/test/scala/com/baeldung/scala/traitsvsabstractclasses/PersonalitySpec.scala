package com.baeldung.scala.traitsvsabstractclasses

import org.scalatest.GivenWhenThen
import org.scalatest.funspec.AnyFunSpec

class PersonalitySpec extends AnyFunSpec with GivenWhenThen {

  describe("A Poet") {
    Given("an fresh instance of a Poet")
    val john = new Poet

    When("a cheer is requested (poets are kind)")
    val cheer = john.cheer

    Then("a nice salutation should be received")
    assert(cheer == "Hi, it's good to see you")

    When("the poet is weeping")
    val weep = john.weep

    Then("the received message should be sad")
    assert(weep == "Life is sad")

    When("the poet describes himself")
    val poetDescription = john.express

    Then("the description should be sad too")
    assert(poetDescription == "I'm a sad person")
  }

  describe("A Grinch") {
    Given("an fresh instance of a Grinch")
    val peter = new Grinch

    When("the Grinch is weeping")
    val weep = peter.weep

    Then("the received message should be sad")
    assert(weep == "Life is sad")

    When("the grinch is grumpy (always)")
    val scold = peter.scold

    Then("a scold should be received")
    assert(scold == "What are you doing in my garden?")

    When("the Grinch describes himself")
    val GrinchDescription = peter.express

    Then("the description should be of grumpiness")
    assert(GrinchDescription == "I'm a grumpy person")
  }
}
