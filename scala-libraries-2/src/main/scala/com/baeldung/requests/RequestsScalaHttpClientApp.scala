package com.baeldung.requests

import requests.RequestAuth
import requests.Response
import java.io.FileOutputStream
import scala.io.StdIn
import java.io.PrintWriter

object RequestsScalaHttpClientApp extends App {

  def createTempFile() = {
    new PrintWriter("file.txt") {
      try { write("this is a file content") }
      finally { close }
    }
  }

  def simpleGetRequest = {
    val r: Response = requests.get("https://api.github.com/users/baeldung")
    assert(r.text.contains("http://www.baeldung.com"))
    assert(r.statusCode == 200)
    assert(r.contentType.exists(_.contains("application/json")))
    assert(r.is2xx)
  }

  def getWithArgs = {
    val r = requests.get("http://httpbin.org/get?key=value")
    assert(r.statusCode == 200)
  }

  def getWithArgsAsMap = {
    val r =
      requests.get("http://httpbin.org/get", params = Map("key" -> "value"))
    assert(r.statusCode == 200)
  }

  def simplePost = {
    val r =
      requests.post("http://httpbin.org/post", data = Map("key" -> "value"))
    assert(r.statusCode == 200)
  }

  def postFileContent = {
    val r =
      requests.post(
        "http://httpbin.org/post",
        data = new java.io.File("./file.txt")
      )
    assert(r.statusCode == 200)
  }

  def simplePostString = {
    val jsonString = """{"key":"value"}"""
    val r = requests.post("http://httpbin.org/post", data = jsonString)
    assert(r.statusCode == 200)
  }

  def postWithAuth = {
    val r =
      requests.post("http://httpbin.org/post", auth = ("username", "password"))
    assert(r.statusCode == 200)
  }

  def getWithAuth = {
    val r = requests.get(
      "https://postman-echo.com/basic-auth",
      auth = ("postman", "password")
    )
    assert(r.statusCode == 200)
    assert(r.text().contains("true"))
  }

  def streamRequest = {
    val out = new FileOutputStream("events.json")
    val stream: geny.Readable =
      requests.get.stream("https://api.github.com/events")
    stream.writeBytesTo(out)
    out.close()
  }

  def streamPost = {
    val dataStream: geny.Readable =
      requests.get.stream("https://api.github.com/events")
    val r = requests.post.stream(
      "https://httpbin.org/post",
      data = dataStream,
      onHeadersReceived = { res =>
        println(new String(res.history.get.bytes))
      }
    )

  }

  def compressedRequest = {
    val r = requests.post(
      "https://httpbin.org/post",
      compress = requests.Compress.Gzip,
      data = "This is a gzipped post request"
    )
    assert(r.statusCode == 200)
    assert(r.text().contains("gzip"))
    assert(
      r.text().contains("data:application/octet-stream;base64,H4sIAAAAAAAAAA==")
    )

    val r2 = requests.post(
      "https://httpbin.org/post",
      compress = requests.Compress.Deflate,
      data = "This is a deflate post request"
    )
    assert(r2.statusCode == 200)
    assert(r2.text().contains("data:application/octet-stream;base64,eJw="))

  }

  def multiPartFileUpload = {
    val r = requests.post(
      "http://httpbin.org/post",
      data = requests.MultiPart(
        requests.MultiItem(
          "name",
          new java.io.File("./file.txt"),
          "myFile.txt"
        ),
        requests.MultiItem("hint", "This is file upload")
      )
    )
    assert(r.text contains ("multipart/form-data"))
  }

  createTempFile
  
  multiPartFileUpload

  compressedRequest

  simpleGetRequest

  getWithArgs

  simplePost

  postWithAuth

  simplePostString

  getWithArgs

  getWithAuth

  postFileContent

  streamRequest

  streamPost

}
