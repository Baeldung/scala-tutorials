package com.baeldung.scala.traitsvsabstractclasses

object MultipleInheritance {

  trait Reader {
    def read()
  }

  trait Writer {
    def write()
  }

  class File extends Reader with Writer {
    override def read(): Unit = {
      // read file
    }

    override def write(): Unit = {
      // write file
    }
  }
}
