package com.baeldung.scala.traitsvsabstractclasses

object Stackability {

  abstract class File {
    def read(): String
  }

  class MyFile extends File {
    def read(): String = {
      "Contents_Of_File"
    }
  }

  trait UpperCase extends File {
    abstract override def read(): String = {
      super.read().toUpperCase
    }
  }

  trait ReplaceUnderscoresWithSpace extends File {
    abstract override def read(): String = {
      super.read().replaceAll("_", " ")
    }
  }

  trait LowerCase extends File {
    abstract override def read(): String = {
      super.read().toLowerCase
    }
  }
}