package com.baeldung.scala.awscala

import awscala.s3.{Bucket, S3}
import software.amazon.awssdk.auth.credentials.{
  AwsBasicCredentials,
  StaticCredentialsProvider
}
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.{
  CreateBucketRequest,
  HeadBucketRequest
}

import java.net.URI
import scala.util.Try

object Main extends App {
  private def createBucketWithJavaSDK(name: String): Boolean = {
    val s3 = S3Client
      .builder()
      .region(Region.US_WEST_1)
      .build()

    Try {
      s3.createBucket { req: CreateBucketRequest.Builder => req.bucket(name) }

      s3.headBucket(
        HeadBucketRequest.builder().bucket(name).build()
      )
    }.fold(_ => false, _ => true)
  }

  private def createBucketWithAWScala(name: String): Boolean = {
    val s3 = S3.at(awscala.Region.NorthernCalifornia)
    Try {
      val bucket = s3.createBucket(name)
      s3.buckets contains bucket
    }.fold(_ => false, _ => true)
  }

  assert(createBucketWithJavaSDK("java-sdk"))
  assert(createBucketWithAWScala("awscala"))
}
