package com.baeldung.requests

import requests.Response
import java.io.FileOutputStream
import java.io.PrintWriter
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.BeforeAndAfterAll

class RequestsScalaHttpClientLiveTest
  extends AnyWordSpec
  with BeforeAndAfterAll {

  override protected def beforeAll(): Unit = createTempFile()

  // create temp file for testing
  def createTempFile() = {
    new PrintWriter("file.txt") {
      try { write("this is a file content") }
      finally { close }
    }
  }

  "Requests" should {
    "invoke a simple GET request" in {
      val r: Response = requests.get("https://api.github.com/users/baeldung")
      assert(r.text.contains("http://www.baeldung.com"))
      assert(r.statusCode == 200)
      assert(r.contentType.exists(_.contains("application/json")))
      assert(r.is2xx)
    }

    "invoke a GET request with query params" in {
      val r = requests.get("http://httpbin.org/get?key=value")
      assert(r.statusCode == 200)
    }

    "invoke a GET requests with arguments as a MAP" in {
      val r =
        requests.get("http://httpbin.org/get", params = Map("key" -> "value"))
      assert(r.statusCode == 200)
    }

    "invoke a simple POST request" in {
      val r =
        requests.post("http://httpbin.org/post", data = Map("key" -> "value"))
      assert(r.statusCode == 200)
    }

    "invoke a POST request using content from a file" in {
      val r =
        requests.post(
          "http://httpbin.org/post",
          data = new java.io.File("./file.txt")
        )
      assert(r.statusCode == 200)
    }

    "invoke a simple POST request with a JSON string" in {
      val jsonString = """{"key":"value"}"""
      val r = requests.post("http://httpbin.org/post", data = jsonString)
      assert(r.statusCode == 200)
    }

    "invoke a POST request with authentication" in {
      val r =
        requests.post(
          "http://httpbin.org/post",
          auth = ("username", "password")
        )
      assert(r.statusCode == 200)
    }

    "invoke a GET request with basic authentication" in {
      val r = requests.get(
        "https://postman-echo.com/basic-auth",
        auth = ("postman", "password")
      )
      assert(r.statusCode == 200)
      assert(r.text().contains("true"))
    }

    "stream a request and write to a file" in {
      val out = new FileOutputStream("events.json")
      val stream: geny.Readable =
        requests.get.stream("https://api.github.com/events")
      stream.writeBytesTo(out)
      out.close()
    }

    "POST a request as a stream" in {
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

    "invoke a POST request with GZIP and Deflate compression" in {
      val r = requests.post(
        "https://httpbin.org/post",
        compress = requests.Compress.Gzip,
        data = "This is a gzipped post request"
      )
      assert(r.statusCode == 200)
      assert(r.text().contains("gzip"))
      assert(
        r.text()
          .contains("data:application/octet-stream;base64")
      )

      val r2 = requests.post(
        "https://httpbin.org/post",
        compress = requests.Compress.Deflate,
        data = "This is a deflate post request"
      )
      assert(r2.statusCode == 200)
      assert(r2.text().contains("data:application/octet-stream;base64,eJw="))
    }

    "upload a file using multi part file upload" in {
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

  }

}
