package com.baeldung.scala.fileio

import java.io._
import scala.util.{Random, Try}

class FileIO {
  val random = Random

  /** FileWriter Java API
    */
  val fileWriter = new FileWriter(new File("/tmp/hello.txt"))
  fileWriter.write("hello there")
  fileWriter.close()

  /** Using a PrintWriter to format text
    */
  val writer = new PrintWriter(new File("data.txt"))
  val s = "big"
  val numberOfLines = 3000000
  writer.printf(
    "This is a %s program with %d of code",
    s,
    new Integer(numberOfLines)
  )
  writer.close()

  /** Inefficient way to write primitives
    */
  val printWriter = new PrintWriter(new FileOutputStream(new File("data.txt")))
  for (_ <- 1 to 10000) { printWriter.write(random.nextDouble().toString) }
  printWriter.close()

  /** A more efficient way to write primitives
    */
  val dataOutputStream = new DataOutputStream(
    new FileOutputStream(new File("data.txt"))
  )
  for (_ <- 1 to 10000) {
    dataOutputStream.writeDouble(random.nextDouble())
  }
  dataOutputStream.close()

  /** Efficiently handling file writing
    */
  Try {
    val fileWriter = new FileWriter(new File("data.txt"))
    fileWriter.write("Hello World!")
    fileWriter.close()
  }.toEither match {
    case Left(ex) =>
    // handle exception: ex
    case Right(_) => // write operation was successful
  }

  /** Buffered PrintWriter
    */
  val bufferedPrintWriter = new BufferedWriter(
    new PrintWriter(new File("data.txt"))
  )

  for (_ <- 1 to 10000) {
    bufferedPrintWriter.write(random.nextDouble().toString)
  }
  bufferedPrintWriter.close()

  /** Reading from a file
    */
  val fileName = "data.txt"
  val bufferedSource = scala.io.Source.fromFile(fileName)
  for (lines <- bufferedSource.getLines()) {
    // do something with lines
  }
  bufferedSource.close()

  /** Dangerous read that could easily cause an OOM error
    */
  val text = bufferedSource.getLines().mkString
  bufferedSource.close()

  /** Efficiently reading from a file and closing the buffered source
    */
  Try {
    val bufferedSource = scala.io.Source.fromFile(fileName)
    for (lines <- bufferedSource.getLines()) {
      // do something with lines
    }
    bufferedSource
  }.toEither match {
    case Left(error) =>
    // handle error
    case Right(bufferedSource) =>
      // close buffered source
      bufferedSource.close()
  }

  /** Tail-recursively Reading from a file with Java API
    */
  val fileReader = new BufferedReader(new FileReader(fileName))
  def handleRead(line: String): Unit = {
    // handle line that was read
    val newLine = fileReader.readLine()
    if (newLine != null) // if there are more lines to read
      handleRead(newLine)
  }
  handleRead(fileReader.readLine())
  fileReader.close()

}
