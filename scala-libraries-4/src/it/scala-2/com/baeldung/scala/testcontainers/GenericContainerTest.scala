package com.baeldung.scala.testcontainers

import com.baeldung.scala.testcontainers.MyLocalStackContainer.LocalStackPort
import com.dimafeng.testcontainers.GenericContainer
import com.dimafeng.testcontainers.scalatest.TestContainerForEach
import org.scalatest.Ignore
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

import java.net.URI
import java.nio.file.Paths
import scala.collection.JavaConverters._
import scala.util.{Random, Try}

class MyLocalStackContainer private (
  hostPort: Int,
  underlying: GenericContainer
) extends GenericContainer(underlying) {
  underlying.container.setPortBindings(
    List(s"$hostPort:$LocalStackPort").asJava
  )

  val endpoint = s"http://localhost:$hostPort"
  val accessKeyId = "not_used"
  val secretAccessKey = "not_used"
}

object MyLocalStackContainer {
  private val LocalStackPort = 4566

  case class Def(hostPort: Int)
    extends GenericContainer.Def[MyLocalStackContainer](
      new MyLocalStackContainer(
        hostPort,
        GenericContainer(
          dockerImage = "localstack/localstack:1.3.0",
          exposedPorts = Seq(LocalStackPort),
          waitStrategy = new LogMessageWaitStrategy().withRegEx(".*Ready\\.\n")
        )
      )
    )
}

@Ignore
class GenericContainerTest
  extends AnyFlatSpec
  with Matchers
  with TestContainerForEach {

  private val BucketName = Random.alphanumeric.take(10).mkString.toLowerCase

  override val containerDef: MyLocalStackContainer.Def =
    MyLocalStackContainer.Def(5000)

  "SimpleS3Uploader" should "upload a file in the desired bucket" in {
    withContainers { ls =>
      val region = "us-east-1"

      val s3 = S3Client
        .builder()
        .region(Region.of(region))
        .endpointOverride(new URI(ls.endpoint))
        .credentialsProvider(
          StaticCredentialsProvider.create(
            AwsBasicCredentials.create(ls.accessKeyId, ls.secretAccessKey)
          )
        )
        .forcePathStyle(true)
        .build()

      s3.createBucket(CreateBucketRequest.builder().bucket(BucketName).build)

      new SimpleS3Uploader(
        region = region,
        endpoint = new URI(ls.endpoint),
        accessKeyId = ls.accessKeyId,
        secretAccessKey = ls.secretAccessKey
      ).upload(BucketName, Paths.get("build.sbt"))

      Try(
        s3.headObject(
          HeadObjectRequest
            .builder()
            .bucket(BucketName)
            .key("build.sbt")
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
