// FileSystem.sc

import scala.io.Source

@main
def countLines(path: String): Unit = {
  val src = Source.fromFile(path)
  println(src.getLines.size)
  src.close()
}