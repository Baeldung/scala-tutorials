package com.baeldung.scala.traitsvsabstractclasses

import org.scalatest.funsuite.AnyFunSuite

class PersonalityTest extends AnyFunSuite {
  test("testPersonality") {
    val john = new Poet
    assertResult("Hi, it's good to see you")(john.cheer)
    assertResult("Life is sad")(john.weep)
    assertResult("I'm a sad person")(john.express)

    val peter = new Grinch
    assertResult("Life is sad")(peter.weep)
    assertResult("What are you doing in my garden?")(peter.scold)
    assertResult("I'm a grumpy person")(peter.express)
  }
}
