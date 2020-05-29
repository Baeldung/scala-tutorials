package com.baeldung.scala.difference.traitandabstractclass

import org.scalatest.{ FlatSpec, GivenWhenThen, Matchers }

class ImagePublisherUnitTest extends FlatSpec with Matchers with GivenWhenThen {

  "Objects" should "extend both trait and abstract class" in {

    Given("InstagramBaseImagePublisher")

    When("we apply the image processes to the image")
    val result: Seq[String] = InstagramBaseImagePublisher.apply()

    Then("the BaseImageProcess is applied")
    assert(
      result.equals(
        Seq("Applying a base processing for image")))

    Then("the applied process name is BaseImageProcess")
    assert(
      InstagramBaseImagePublisher.getProcessName.equals("BaseImageProcess"))

    Then("it is possible to publish post")
    assert(InstagramBaseImagePublisher.publish.equals(true))

    Then("it is possible to delete post")
    assert(InstagramBaseImagePublisher.delete.equals(true))
  }
}
