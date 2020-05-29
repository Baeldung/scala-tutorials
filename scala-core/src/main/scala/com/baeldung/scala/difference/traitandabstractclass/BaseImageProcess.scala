package com.baeldung.scala.difference.traitandabstractclass

trait BaseImageProcess {
  def apply(): Seq[String] = Seq("Applying a base processing for image")
  def getProcessName: String = "BaseImageProcess"
}

trait CropBaseImageProcess extends BaseImageProcess {
  abstract override def apply(): Seq[String] =
    super.apply() ++ Seq(s"Applying cropping image")

  abstract override def getProcessName: String = "CropImageProcess"
}

trait ColorBaseImageEnhancementProcess extends BaseImageProcess {
  abstract override def apply(): Seq[String] =
    super.apply() ++ Seq(s"Applying color enhancement for image")

  abstract override def getProcessName: String = "ColorImageEnhancementProcess"
}

trait BaseImageCompressionProcess extends BaseImageProcess {
  abstract override def apply(): Seq[String] =
    super.apply() ++ Seq(s"Applying image compression")

  abstract override def getProcessName: String = "ImageCompressionProcess"
}

class BaseImageProcessImpl() extends BaseImageProcess {

  override def apply(): Seq[String] = {
    super.apply()
  }

  override def getProcessName(): String = {
    "Image process implementation for generic social network post."
  }
}