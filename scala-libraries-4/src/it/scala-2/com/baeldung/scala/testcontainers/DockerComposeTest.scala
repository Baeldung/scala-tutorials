package com.baeldung.scala.testcontainers

import com.dimafeng.testcontainers.scalatest.TestContainerForEach
import org.scalatest.Ignore
import com.dimafeng.testcontainers.{DockerComposeContainer, ExposedService}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy
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

/** use sbt command to run the test for e.g.: sbt "it:testOnly
  * *DockerComposeTest". When you run in IntelliJ IDEA and if you get error
  * regarding the resources, then mark the src/it/resources directory as "test
  * resources" in intellij.
  */
@Ignore
//ignored since this needs docker environment, which is not available in jenkins
class DockerComposeTest
  extends AnyFlatSpec
  with Matchers
  with TestContainerForEach {

  private val BucketName = Random.alphanumeric.take(10).mkString.toLowerCase
  private val ExposedPort = 5000
  private val uploadFile = new File(
    getClass.getClassLoader.getResource("s3-test.txt").getFile
  )

  override lazy val containerDef: DockerComposeContainer.Def = {
    DockerComposeContainer.Def(
      new File(
        this.getClass.getClassLoader.getResource("docker-compose.yml").getFile
      ),
      exposedServices = Seq(
        ExposedService(
          "localstack",
          ExposedPort,
          new LogMessageWaitStrategy().withRegEx(".*Ready\\.\n")
        )
      )
    )
  }

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
    ).upload(BucketName, uploadFile.toPath)

    Try(
      s3.headObject(
        HeadObjectRequest
          .builder()
          .bucket(BucketName)
          .key(uploadFile.getName)
          .build()
      )
    ).fold(
      {
        case ex: NoSuchKeyException => fail("File not found: " + ex)
        case _                      => fail
      },
      _ => succeed
    )
  }
}
