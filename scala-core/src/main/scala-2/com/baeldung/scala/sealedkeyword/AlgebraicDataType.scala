package com.baeldung.scala.sealedkeyword

class AlgebraicDataType {

  sealed trait Coffee

  case object Cappuccino extends Coffee
  case object Americano extends Coffee
  case object Espresso extends Coffee
}
