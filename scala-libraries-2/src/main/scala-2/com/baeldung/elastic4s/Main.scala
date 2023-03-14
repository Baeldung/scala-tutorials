package com.baeldung.elastic4s

import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.fields.{DateField, IpField, TextField}
import com.sksamuel.elastic4s.http.JavaClient
import com.sksamuel.elastic4s.requests.cat.CatIndicesResponse
import com.sksamuel.elastic4s.requests.common.RefreshPolicy
import com.sksamuel.elastic4s.requests.indexes.admin.IndexExistsResponse
import com.sksamuel.elastic4s.requests.searches.SearchResponse
import com.sksamuel.elastic4s.{
  ElasticClient,
  ElasticProperties,
  RequestFailure,
  RequestSuccess,
  Response
}

import java.time.Instant
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success

object Main extends App {

  val client = ElasticClient(
    JavaClient(ElasticProperties("http://localhost:9200"))
  )

  // create index
  client.execute {
    createIndex("activities").mapping(
      properties(
        TextField("username"),
        DateField("when"),
        IpField("ip"),
        TextField("action")
      )
    )
  }.await

  // verify the index exists
  client.execute {
    catIndices()
  }.await match {
    case failure: RequestFailure => println(failure.error)
    case response: Response[Seq[CatIndicesResponse]] =>
      println(response.result.exists(_.index == "activities"))
  }

  client.execute {
    indexExists("activities")
  }.await match {
    case failure: RequestFailure => println(failure.error)
    case response: RequestSuccess[IndexExistsResponse] =>
      println(response.result.exists)
  }

  // insert documents
  client
    .execute {
      indexInto("activities")
        .fields(
          "username" -> "thomas",
          "when" -> Instant.now,
          "ip" -> "192.168.197.123",
          "action" -> "GetArticles"
        )
        .refresh(RefreshPolicy.Immediate)
    }
    .andThen { case Success(_) =>
      client.execute(
        indexInto("activities")
          .fields(
            "username" -> "robert",
            "when" -> Instant.now,
            "ip" -> "192.168.197.103",
            "action" -> "DeleteArticle"
          )
          .refreshImmediately
      )
    }
    .await

  // list documents
  client.execute {
    search("activities")
  }.await match {
    case failure: RequestFailure => println(failure.error)
    case response: RequestSuccess[SearchResponse] =>
      val searchHits = response.result.hits.hits.toList
      searchHits.foreach { searchHit =>
        println(s"${searchHit.id} -> ${searchHit.sourceAsMap}")
      }
  }

  // delete documents
  client.execute {
    deleteByQuery(
      "activities",
      termQuery("username", "robert")
    ).refreshImmediately
  }.await

  client.close()
}
