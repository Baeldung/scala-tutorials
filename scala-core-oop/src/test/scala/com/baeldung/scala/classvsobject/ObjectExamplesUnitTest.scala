package com.baeldung.scala.classvsobject

import com.baeldung.scala.classvsobject.ObjectExamples.BaeldungEnvironment._
import com.baeldung.scala.classvsobject.ObjectExamples.{BaeldungEnvironment, Router}
import com.baeldung.scala.classvsobject.ObjectExamples.Router.Response
import org.junit.Assert._
import org.junit.Test

class ObjectExamplesUnitTest {

  val index_router = new Router("/index")

  @Test
  def givenRouterCompanionObjectAndClass_whenGetIsCalled_thenAGetResponseIsReturned(): Unit = {
    assertEquals(index_router.get(), Response("https://www.baeldung.com","/index","GET"))
  }

  @Test
  def givenRouterCompanionObjectAndClass_whenPostIsCalled_thenAPostResponseIsReturned(): Unit = {
    assertEquals(index_router.post(), Response("https://www.baeldung.com","/index","POST"))
  }

  @Test
  def givenRouterCompanionObjectAndClass_whenPutIsCalled_thenAPutResponseIsReturned(): Unit = {
    assertEquals(index_router.put(), Response("https://www.baeldung.com","/index","PUT"))
  }

  @Test
  def givenRouterCompanionObjectAndClass_whenPatchIsCalled_thenAPatchResponseIsReturned(): Unit = {
    assertEquals(index_router.patch(), Response("https://www.baeldung.com","/index","PATCH"))
  }

  @Test
  def givenRouterCompanionObjectAndClass_whenDeleteIsCalled_thenADeleteResponseIsReturned(): Unit = {
    assertEquals(index_router.delete(), Response("https://www.baeldung.com","/index","DELETE"))
  }

  @Test
  def givenBaeldungEnvironment_whenFromEnvStringIsCalledWithTest_thenTestEnvironmentIsReturned(): Unit ={
    val test = BaeldungEnvironment.fromEnvString("test")

    assertEquals(test, Some(TestEnvironment()))
  }

  @Test
  def givenBaeldungEnvironment_whenFromEnvStringIsCalledWithInt_thenIntEnvironmentIsReturned(): Unit ={
    val int = BaeldungEnvironment.fromEnvString("int")

    assertEquals(int, Some(IntEnvironment()))
  }

  @Test
  def givenBaeldungEnvironment_whenFromEnvStringIsCalledWithStaging_thenStagingEnvironmentIsReturned(): Unit ={
    val stg = BaeldungEnvironment.fromEnvString("staging")

    assertEquals(stg, Some(StagingEnvironment()))
  }

  @Test
  def givenBaeldungEnvironment_whenFromEnvStringIsCalledWithProduction_thenProductionEnvironmentIsReturned(): Unit ={
    val prod = BaeldungEnvironment.fromEnvString("production")

    assertEquals(prod, Some(ProductionEnvironment()))
  }

}
