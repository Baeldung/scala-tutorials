package com.baeldung.scala.difference.traitandabstractclass;

import org.scalatest._

class BaseImageProcessUnitTest extends FlatSpec with Matchers {

  "Image process flow" should "enhance correctly the image" in {
    // Given
    val imageProcessor = new BaseImageProcessImpl()
      with CropBaseImageProcess
      with ColorBaseImageEnhancementProcess
      with BaseImageCompressionProcess

    // When
    val result: Seq[String] = imageProcessor.apply()

    // Then
    val expectedEnhanements = Seq(
        "Applying a base processing for image",
        "Applying cropping image",
        "Applying color enhancement for image",
        "Applying image compression"
    )
    assert(
        result.equals(
            expectedEnhanements
        )
    )
  }
}
