package com.baeldung.scala.traitsvsabstractclasses

// Abstract classes can take parameters:
abstract class Language(val name: String, val helloString: String) {
  def hello(recipient: String): String = s"$helloString, $recipient"
  def goodbye: String
}

// The classes French and German need to override `goodbye()`,
// but they use the default `hello()`:
class French extends Language(name = "French", helloString = "Bon jour") {
  override def goodbye: String = "Au revoir!"
}

class German extends Language(name = "German", helloString = "Guten Tag") {
  override def goodbye: String = "Auf Wiedersehen!"
}
