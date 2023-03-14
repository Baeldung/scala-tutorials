package com.baeldung.enumeratum

import enumeratum._
import scala.collection.immutable._

sealed trait Country extends EnumEntry
object Country extends Enum[Country] {
  case object Germany extends Country
  case object India extends Country

  override val values = findValues
}

sealed abstract class Gender(override val entryName: String) extends EnumEntry
object Gender extends Enum[Gender] {
  override def values: IndexedSeq[Gender] = findValues
  case object Male extends Gender("M")
  case object Female extends Gender("F")
  case object Other extends Gender("O")
}

sealed trait Continent extends EnumEntry with EnumEntry.UpperWords
object Continent extends Enum[Continent] {
  override val values = findValues
  case object Asia extends Continent
  case object Africa extends Continent
  case object Europe extends Continent
  case object NorthAmerica extends Continent
  case object SouthAmerica extends Continent
  case object Australia extends Continent
  case object Antartica extends Continent
}

sealed trait NamingConvention extends EnumEntry with EnumEntry.LowerCamelcase
object NamingConvention extends Enum[NamingConvention] {
  override val values = findValues
  case object JavaStyle extends NamingConvention
  case object ScalaStyle extends NamingConvention
  case object PythonStyle extends NamingConvention with EnumEntry.Snakecase
}

import enumeratum.values._
sealed abstract class HttpCode(val value: Int, val name: String)
  extends IntEnumEntry
object HttpCode extends IntEnum[HttpCode] {
  override def values = findValues
  case object OK extends HttpCode(200, "Ok")
  case object BadRequest extends HttpCode(400, "Bad Request")
}
