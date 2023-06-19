package com.baeldung.scala.testcontainers

import com.dimafeng.testcontainers.LocalStackV2Container
import com.dimafeng.testcontainers.scalatest.TestContainerForEach
import org.scalatest.Ignore
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.testcontainers.containers.localstack.LocalStackContainer.Service
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.{
  CreateBucketRequest,
  HeadObjectRequest,
  NoSuchKeyException
}

import java.nio.file.Paths
import scala.util.{Random, Try}

/** use sbt command to run the test for e.g.: sbt "it:testOnly
  * *LocalstackModuleTest". When you run in IntelliJ IDEA and if you get error
  * regarding the resources, then mark the src/it/resources directory as "test
  * resources" in intellij.
  */
@Ignore
//ignored since this needs docker environment, which is not available in jenkins
class LocalstackModuleManualTest
  extends AnyFlatSpec
  with Matchers
  with TestContainerForEach {

  private val BucketName = Random.alphanumeric.take(10).mkString.toLowerCase

  override val containerDef: LocalStackV2Container.Def =
    LocalStackV2Container.Def(
      tag = "1.3.0",
      services = Seq(Service.S3)
    )

  "SimpleS3Uploader" should "upload a file in the desired bucket" in {
    withContainers { ls =>
      val s3 = S3Client
        .builder()
        .region(ls.region)
        .endpointOverride(ls.endpointOverride(Service.S3))
        .credentialsProvider(ls.staticCredentialsProvider)
        .forcePathStyle(true)
        .build()

      s3.createBucket(CreateBucketRequest.builder().bucket(BucketName).build)

      new SimpleS3Uploader(
        region = ls.region.toString,
        endpoint = ls.endpointOverride(Service.S3),
        accessKeyId = ls.container.getAccessKey,
        secretAccessKey = ls.container.getSecretKey
      ).upload(
        BucketName,
        Paths.get(getClass.getClassLoader.getResource("s3-test.txt").toURI)
      )

      Try(
        s3.headObject(
          HeadObjectRequest
            .builder()
            .bucket(BucketName)
            .key("s3-test.txt")
            .build()
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
}
