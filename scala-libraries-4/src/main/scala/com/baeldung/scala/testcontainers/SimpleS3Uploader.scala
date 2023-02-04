package com.baeldung.scala.testcontainers

import software.amazon.awssdk.auth.credentials.{
  AwsBasicCredentials,
  StaticCredentialsProvider
}
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest

import java.net.URI
import java.nio.file.Path

class SimpleS3Uploader(
  region: String,
  endpoint: URI,
  accessKeyId: String,
  secretAccessKey: String
) {
  private lazy val s3 = S3Client
    .builder()
    .region(Region.of(region))
    .endpointOverride(endpoint)
    .credentialsProvider(
      StaticCredentialsProvider.create(
        AwsBasicCredentials.create(accessKeyId, secretAccessKey)
      )
    )
    .forcePathStyle(true)
    .build()

  def upload(bucket: String, filePath: Path): Unit = {
    s3.putObject(
      PutObjectRequest
        .builder()
        .bucket(bucket)
        .key(filePath.getFileName.toString)
        .build(),
      filePath
    )
  }
}
