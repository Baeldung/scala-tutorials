package com.baeldung.scala.difference.traitandabstractclass

abstract class ImagePublisher(imagePath: String) {
  def publish: Boolean
  def delete: Boolean
}

class ImagePublisherImpl(imagePath: String) extends ImagePublisher(imagePath: String) {
  override def publish: Boolean = true
  override def delete: Boolean = true
}

object FacebookImagePublisher {
  // will not compile
  // val publisher: ImagePublisher

  val imagePath: String = "/path/image.jpg"
  val publisher = new ImagePublisherImpl(imagePath)
  var imageProcess: Seq[BaseImageProcess] = _
}

object InstagramBaseImagePublisher extends ImagePublisher(imagePath = "/path/image.jpg") with BaseImageProcess {

  val imagePath: String = "/path/image.jpg"
  val publisher = new ImagePublisherImpl(imagePath)

  override def publish: Boolean = true

  override def delete: Boolean = true
}
