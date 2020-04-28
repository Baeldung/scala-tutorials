package com.baeldung.scala.traitsvsabstractclasses

object SubClassEnforcement {

  class Buffered {
    //buffered operations
  }

  trait Reader extends Buffered {
    def read()
  }

  class BufferedFile extends Buffered with Reader {
    override def read(): Unit = {
      //read buffered file
    }
  }
}
