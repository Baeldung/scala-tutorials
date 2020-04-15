package com.baeldung.scala.traitabstractclass.selftypemixin

import scala.concurrent.Future

abstract class Databases[F[_]]

trait StaticRoles[F[_]] { self: Databases[F] =>
  def abc: String
}

class DbService extends Databases[Future] with StaticRoles[Future] {
  override def abc: String = ???
}
