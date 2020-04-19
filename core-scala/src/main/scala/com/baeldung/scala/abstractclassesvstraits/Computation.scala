package com.baeldung.scala.abstractclassesvstraits


trait Computation[A] {

  def identifier : String

  def compute : A

}






