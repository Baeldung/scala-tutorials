package com.baeldung.scala3.macros.inline

import scala.util.Random
import scala.compiletime.*

object InlineCompilerError {
  inline def checkPort(portNo: Int) = {
    inline if (portNo < 8080 || portNo > 9000) {
      error("Invalid port number! ")
    } else {
      println(s"Port number $portNo will be used for the app")
    }
  }

  @main
  def compilerErrorMain =
    checkPort(8090)
}
