package com.baeldung.scala.implicitly

import com.baeldung.scala.implicitly.ImplicitlyUsage.{Customer, Policy, searchWithContextBound, searchWithImplicit, weight, weightUsingImplicit}
import org.scalatest.FlatSpec

class ImplicitlyUsageUnitTest extends FlatSpec {

  "The weight method" should "return the weight of a mass" in {
    val actualWeight: Double = weight(50.0, 9.81)
    assert(actualWeight == 490.5)
  }

  "The weightUsingImplicit method" should "return the weight of a mass" in {
    import ImplicitlyUsage.G
    val actualWeight: Double = weightUsingImplicit(50.0)
    assert(actualWeight == 490.5)
  }

  "The weightUsingImplicitly method" should "return the weight of a mass" in {
    import ImplicitlyUsage.G
    val actualWeight: Double = weightUsingImplicit(50.0)
    assert(actualWeight == 490.5)
  }

  "A Customer" should "be searchable using the searchWithImplicit method" in {
    val customer = Customer("123456", "Will", "Smith")
    val uri = searchWithImplicit(customer)
    assert(uri == "/customers/123456")
  }

  it should "be searchable using the searchWithContextBound method" in {
    val customer = Customer("123456", "Will", "Smith")
    val uri = searchWithContextBound(customer)
    assert(uri == "/customers/123456")
  }

  "A Policy" should "be searchable using the searchWithImplicit method" in {
    val policy = Policy("09876", "A policy")
    val uri = searchWithImplicit(policy)
    assert(uri == "/policies/09876")
  }

  it should "be searchable using the searchWithContextBound method" in {
    val policy = Policy("09876", "A policy")
    val uri = searchWithContextBound(policy)
    assert(uri == "/policies/09876")
  }
}
