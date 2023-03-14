package com.baeldung.scala.testcontainers

import com.dimafeng.testcontainers.{DockerComposeContainer, ExposedService}
import com.dimafeng.testcontainers.scalatest.TestContainerForEach
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.testcontainers.containers.wait.strategy.{
  LogMessageWaitStrategy,
  Wait
}
import software.amazon.awssdk.auth.credentials.{
  AwsBasicCredentials,
  StaticCredentialsProvider
}
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.{
  CreateBucketRequest,
  HeadObjectRequest,
  NoSuchKeyException
}

import java.io.File
import java.net.URI
import java.nio.file.Paths
import scala.util.{Random, Try}

class DockerComposeTest
  extends AnyFlatSpec
  with Matchers
  with TestContainerForEach {

  private val BucketName = Random.alphanumeric.take(10).mkString.toLowerCase
  private val ExposedPort = 5000

  override val containerDef: DockerComposeContainer.Def =
    DockerComposeContainer.Def(
      new File("scala-libraries-4/src/it/resources/docker-compose.yml"),
      exposedServices = Seq(
        ExposedService(
          "localstack",
          ExposedPort,
          new LogMessageWaitStrategy().withRegEx(".*Ready\\.\n")
        )
      )
    )

  "SimpleS3Uploader" should "upload a file in the desired bucket" in {
    val region = "us-east-1"
    val endpoint = s"http://localhost:$ExposedPort"

    val s3 = S3Client
      .builder()
      .region(Region.of(region))
      .endpointOverride(new URI(endpoint))
      .credentialsProvider(
        StaticCredentialsProvider.create(
          AwsBasicCredentials.create("not_used", "not_used")
        )
      )
      .forcePathStyle(true)
      .build()

    s3.createBucket(CreateBucketRequest.builder().bucket(BucketName).build)

    new SimpleS3Uploader(
      region = region,
      endpoint = new URI(endpoint),
      accessKeyId = "not_used",
      secretAccessKey = "not_used"
    ).upload(BucketName, Paths.get("build.sbt"))

    Try(
      s3.headObject(
        HeadObjectRequest.builder().bucket(BucketName).key("build.sbt").build()
      )
    ).fold(
      {
        case _: NoSuchKeyException => fail("File not found")
        case _                     => fail
      },
      _ => succeed
    )
  }
}
