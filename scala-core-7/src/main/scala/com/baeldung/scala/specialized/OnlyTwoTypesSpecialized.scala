package com.baeldung.scala.specialized

class OnlyTwoTypesSpecialized[@specialized(Int, Boolean) T] {
  def get(value: T): T = value
}
