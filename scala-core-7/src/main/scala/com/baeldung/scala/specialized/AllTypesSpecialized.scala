package com.baeldung.scala.specialized

class AllTypesSpecialized[@specialized T] {
  def get(value: T): T = value
}
