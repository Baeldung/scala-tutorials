package com.baeldung.scala.traitsvsabstractclasses

sealed trait Personality {
  val description: String
  def express: String = s"I'm a $description person"
}

trait Kind extends Personality {
  override val description: String = "kind"
  def cheer: String
}

trait Grumpy extends Personality {
  override val description: String = "grumpy"
  def scold: String
}

trait Sad extends Personality {
  override val description: String = "sad"
  def weep: String
}

// Poet extends two conflicting traits
// bot the last one wins
class Poet extends Kind with Sad {
  override def cheer: String = "Hi, it's good to see you"
  override def weep: String = "Life is sad"
}

// Again, the conflict is resolved with the last declaration
class Grinch extends Sad with Grumpy {
  override def weep: String = "Life is sad"
  override def scold: String = "What are you doing in my garden?"
}
