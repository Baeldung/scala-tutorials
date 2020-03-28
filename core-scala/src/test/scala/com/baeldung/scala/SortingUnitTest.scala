package com.baeldung.scala

import org.scalatest.{Matchers, WordSpec}

class SortingUnitTest extends WordSpec with Matchers {

  case class User(name: String, age: Int) extends Ordered[User] {
    override def compare(that: User): Int =
      java.lang.Integer.compare(age, that.age)
  }

  val users = List(
    User("Mike", 43),
    User("Mike", 16),
    User("Kelly", 21)
  )

  "Collection of users" should {

    "sort users using ordered" in {
      users.sorted shouldBe List(
        User("Mike", 16),
        User("Kelly", 21),
        User("Mike", 43),
      )
    }

    "sort users using ordering" in {
      implicit val userOrdering: Ordering[User] = Ordering.by(_.age)

      users.sorted shouldBe List(
        User("Mike", 16),
        User("Kelly", 21),
        User("Mike", 43),
      )
    }

    "sort users using reverse ordering" in {
      implicit val userOrdering: Ordering[User] = Ordering.by[User, Int](_.age).reverse

      users.sorted shouldBe List(
        User("Mike", 43),
        User("Kelly", 21),
        User("Mike", 16),
      )
    }

    "sort users by name" in {
      users.sortBy(_.name) shouldBe List(
        User("Kelly", 21),
        User("Mike", 43),
        User("Mike", 16),
      )
    }

    "sort users with compare function" in {
      users.sortWith(_.age > _.age) shouldBe List(
        User("Mike", 43),
        User("Kelly", 21),
        User("Mike", 16),
      )
    }

    "sort users by multiple fields" in {
      users.sortBy(u => (u.name, u.age)) shouldBe List(
        User("Kelly", 21),
        User("Mike", 16),
        User("Mike", 43),
      )
    }
  }
}
