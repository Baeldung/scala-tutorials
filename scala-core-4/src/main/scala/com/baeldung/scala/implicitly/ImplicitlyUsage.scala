package com.baeldung.scala.implicitly

object ImplicitlyUsage {

  implicit val G: Double = 9.81

  def weight(mass: Double, gravitationalConstant: Double): Double =
    mass * gravitationalConstant

  def weightUsingImplicit(mass: Double)(implicit
    gravitationalConstant: Double
  ): Double =
    weight(mass, gravitationalConstant)

  def weightUsingImplicitly(mass: Double): Double = {
    val gravitationalConstant = implicitly[Double]
    weight(mass, gravitationalConstant)
  }

  trait Searchable[T] {
    def uri(obj: T): String
  }

  case class Customer(taxCode: String, name: String, surname: String)
  case class Policy(policyId: String, description: String)

  implicit val searchableCustomer: Searchable[Customer] =
    new Searchable[Customer] {
      override def uri(customer: Customer): String =
        s"/customers/${customer.taxCode}"
    }

  implicit val searchablePolicy: Searchable[Policy] = new Searchable[Policy] {
    override def uri(policy: Policy): String = s"/policies/${policy.policyId}"
  }

  def searchWithImplicit[S](obj: S)(implicit
    searchable: Searchable[S]
  ): String = searchable.uri(obj)

  def searchWithContextBound[S: Searchable](obj: S): String = {
    val searchable = implicitly[Searchable[S]]
    searchable.uri(obj)
  }
}
