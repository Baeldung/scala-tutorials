package com.baeldung.scala.extractorobject

case class Header(key: String, value: String)
class HttpRequest(val method: String, val uri: String, val headers: List[Header])

object HttpRequest {
  def unapplySeq(request: HttpRequest): Option[List[Header]] = Some(request.headers)
}

object RequestApp extends App {

  private val headers = Header("Content-Type", "application/json")::
    Header("Authorization", "token") :: Header("Content-Language", "fa_IR") :: Nil

  val request = new HttpRequest("GET", "localhost", headers)
  request match {
    case HttpRequest(h1) => println(s"The only header is $h1")
    case HttpRequest(h1, h2) => println(s"We have two headers: $h1 and $h2")
    case HttpRequest(all @ _*) => print(s"All headers are as following: $all")
  }
}