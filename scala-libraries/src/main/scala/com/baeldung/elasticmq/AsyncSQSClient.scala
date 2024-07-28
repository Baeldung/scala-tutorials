package com.baeldung.elasticmq

import software.amazon.awssdk.auth.credentials.{
  AwsBasicCredentials,
  AwsCredentialsProviderChain,
  StaticCredentialsProvider
}
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.model.*
import software.amazon.awssdk.services.sqs.{
  SqsAsyncClient,
  SqsAsyncClientBuilder
}

import java.net.URI
import java.util.UUID

import scala.concurrent.{ExecutionContext, Future}
import scala.jdk.FutureConverters.*
import scala.jdk.CollectionConverters.*

class SQSAsyncClient(
  queueURL: String,
  region: String,
  endpoint: String
)(implicit executionContext: ExecutionContext):

  private val sqsAsyncClient: SqsAsyncClient =
    SqsAsyncClient
      .builder()
      .region(Region.of(region))
      .credentialsProvider(
        AwsCredentialsProviderChain
          .builder()
          .credentialsProviders(
            StaticCredentialsProvider.create(
              AwsBasicCredentials.create(
                ElasticMQConfig.ELASTIC_MQ_ACCESS_KEY,
                ElasticMQConfig.ELASTIC_MQ_SECRET_ACCESS_KEY
              )
            )
          )
          .build()
      )
      .endpointOverride(URI.create(endpoint))
      .build()

  def createStandardQueue(queueName: String): Future[CreateQueueResponse] =
    val request = CreateQueueRequest.builder.queueName(queueName).build

    sqsAsyncClient.createQueue(request).asScala

  final lazy val createFIFOQueueAttributes = Map(
    (QueueAttributeName.FIFO_QUEUE, "true")
  ).asJava

  def createFIFOQueue(queueName: String): Future[CreateQueueResponse] =
    val createQueueRequest = CreateQueueRequest.builder
      .queueName(queueName)
      .attributes(createFIFOQueueAttributes)
      .build

    sqsAsyncClient.createQueue(createQueueRequest).asScala

  def deleteQueue(): Future[DeleteQueueResponse] =
    val request = DeleteQueueRequest.builder().queueUrl(queueURL).build()

    sqsAsyncClient.deleteQueue(request).asScala

  def sendMessage(message: String): Future[SendMessageResponse] =
    val request = SendMessageRequest
      .builder()
      .messageBody(message)
      .queueUrl(queueURL)
      .build()

    sqsAsyncClient.sendMessage(request).asScala

  def sendMessagesInBatch(
    messages: List[String]
  ): Future[SendMessageBatchResponse] =
    val batchRequestEntry = messages
      .map(
        SendMessageBatchRequestEntry
          .builder()
          .messageBody(_)
          .id(UUID.randomUUID().toString)
          .build()
      )
      .asJava
    val sendMessageBatchRequest = SendMessageBatchRequest
      .builder()
      .queueUrl(queueURL)
      .entries(batchRequestEntry)
      .build()

    sqsAsyncClient.sendMessageBatch(sendMessageBatchRequest).asScala

  // maxNumberOfMessages must be less than 10.
  def receiveMessages(
    maxNumberOfMessages: Int
  ): Future[ReceiveMessageResponse] =
    val receiveMessageRequest =
      ReceiveMessageRequest
        .builder()
        .maxNumberOfMessages(maxNumberOfMessages)
        .queueUrl(queueURL)
        .waitTimeSeconds(10)
        .build()

    sqsAsyncClient.receiveMessage(receiveMessageRequest).asScala

  def deleteMessage(receiptHandle: String): Future[DeleteMessageResponse] =
    val deleteMessageRequest = DeleteMessageRequest
      .builder()
      .queueUrl(queueURL)
      .receiptHandle(receiptHandle)
      .build()

    sqsAsyncClient.deleteMessage(deleteMessageRequest).asScala

  def deleteMessageInBatch(
    messages: List[Message]
  ): Future[DeleteMessageBatchResponse] =
    val listDeleteMessageBatchRequestEntry = messages
      .map(message =>
        DeleteMessageBatchRequestEntry
          .builder()
          .receiptHandle(message.receiptHandle())
          .build()
      )
      .asJava
    val deleteMessageBatchRequest = DeleteMessageBatchRequest
      .builder()
      .queueUrl(queueURL)
      .entries(listDeleteMessageBatchRequestEntry)
      .build()

    sqsAsyncClient.deleteMessageBatch(deleteMessageBatchRequest).asScala

  def getQueueURL(queueName: String): Future[GetQueueUrlResponse] =
    val getQueueUrlRequest =
      GetQueueUrlRequest.builder().queueName(queueName).build()

    sqsAsyncClient.getQueueUrl(getQueueUrlRequest).asScala

  def listQueues(): Future[ListQueuesResponse] =
    sqsAsyncClient.listQueues().asScala

  def listQueuesStartingFromPrefix(prefix: String): Future[ListQueuesResponse] =
    val listQueueStartingFromPrefixRequest =
      ListQueuesRequest.builder().queueNamePrefix(prefix).build()

    sqsAsyncClient.listQueues(listQueueStartingFromPrefixRequest).asScala

  def changeMessageVisibility(
    message: Message
  ): Future[ChangeMessageVisibilityResponse] =
    val changeMessageVisibilityRequest = ChangeMessageVisibilityRequest
      .builder()
      .queueUrl(queueURL)
      .receiptHandle(message.receiptHandle())
      .visibilityTimeout(30)
      .build()

    sqsAsyncClient
      .changeMessageVisibility(changeMessageVisibilityRequest)
      .asScala

  def changeMessageVisibilityOfBatch(
    messages: List[Message]
  ): Future[ChangeMessageVisibilityBatchResponse] =
    val changeMessageVisibilityBatchRequestEntry = messages
      .map(message =>
        ChangeMessageVisibilityBatchRequestEntry
          .builder()
          .receiptHandle(message.receiptHandle())
          .visibilityTimeout(30)
          .build()
      )
      .asJava
    val changeMessageVisibilityRequest = ChangeMessageVisibilityBatchRequest
      .builder()
      .queueUrl(queueURL)
      .entries(changeMessageVisibilityBatchRequestEntry)
      .build()

    sqsAsyncClient
      .changeMessageVisibilityBatch(changeMessageVisibilityRequest)
      .asScala

  final lazy val purgeQueueRequest =
    PurgeQueueRequest.builder().queueUrl(queueURL).build()
  def purgeQueue(): Future[PurgeQueueResponse] =
    sqsAsyncClient.purgeQueue(purgeQueueRequest).asScala

  def setQueueAttributes(
    attributes: Map[QueueAttributeName, String]
  ): Future[SetQueueAttributesResponse] =
    val setQueueAttributesRequest = SetQueueAttributesRequest
      .builder()
      .queueUrl(queueURL)
      .attributes(attributes.asJava)
      .build()

    sqsAsyncClient.setQueueAttributes(setQueueAttributesRequest).asScala

  def tagQueue(tags: Map[String, String]): Future[TagQueueResponse] =
    val tagQueueRequest =
      TagQueueRequest.builder().queueUrl(queueURL).tags(tags.asJava).build()

    sqsAsyncClient.tagQueue(tagQueueRequest).asScala

  def untagQueue(listOfTagsToRemove: List[String]): Future[UntagQueueResponse] =
    val untagQueueRequest = UntagQueueRequest
      .builder()
      .queueUrl(queueURL)
      .tagKeys(listOfTagsToRemove.asJava)
      .build()

    sqsAsyncClient.untagQueue(untagQueueRequest).asScala
