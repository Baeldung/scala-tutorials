package com.baeldung.gatling

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object ChainRequestsProvider {

  def simpleRequest(
    requestName: String,
    requestPath: String,
    expectedResponseStatus: Int
  ): ChainBuilder = {
    val request: HttpRequestBuilder = http(requestName)
      .get(requestPath)
      .check(status.is(expectedResponseStatus))
      .check(bodyString.optional.saveAs("sBodyString"))

    exec(session => session.markAsSucceeded)
      .exec(request)
      .doIf(_.isFailed) {
        exec { session =>
          println("***Failure on [" + requestPath + "] endpoint:")
          print("Gatling Session Data: ")
          println(session.attributes.get("sBodyString"))
          session
        }
      }
  }
}
