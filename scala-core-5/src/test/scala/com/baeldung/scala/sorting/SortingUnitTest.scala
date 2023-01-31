package com.baeldung.scala.sorting

import org.junit.Test
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SortingUnitTest extends AnyWordSpec with Matchers {

  case class User(name: String, age: Int) extends Ordered[User] {
    override def compare(that: User): Int =
      java.lang.Integer.compare(age, that.age)
  }

  val users = List(
    User("Mike", 43),
    User("Mike", 16),
    User("Kelly", 21)
  )

  @Test
  def givenUsers_whenSorted_thenSortUsingOrdered(): Unit = {
    users.sorted shouldBe List(
      User("Mike", 16),
      User("Kelly", 21),
      User("Mike", 43),
    )
  }

  @Test
  def givenUsers_whenSorted_thenSortUsingOrdering(): Unit = {
    implicit val userOrdering: Ordering[User] = Ordering.by(_.age)

    users.sorted shouldBe List(
      User("Mike", 16),
      User("Kelly", 21),
      User("Mike", 43),
    )
  }

  @Test
  def givenUsers_whenSorted_thenSortUsingReverseOrdering(): Unit = {
    implicit val userOrdering: Ordering[User] =
      Ordering.by[User, Int](_.age).reverse

    users.sorted shouldBe List(
      User("Mike", 43),
      User("Kelly", 21),
      User("Mike", 16),
    )
  }

  @Test
  def givenUsers_whenSortBy_thenSortByField(): Unit = {
    users.sortBy(_.name) shouldBe List(
      User("Kelly", 21),
      User("Mike", 43),
      User("Mike", 16),
    )
  }

  @Test
  def givenUsers_whenSortWith_thenSortWithCompareFunction(): Unit = {
    users.sortWith(_.age > _.age) shouldBe List(
      User("Mike", 43),
      User("Kelly", 21),
      User("Mike", 16),
    )
  }

  @Test
  def givenUsers_whenSortBy_thenSortByMultipleFields(): Unit = {
    users.sortBy(u => (u.name, u.age)) shouldBe List(
      User("Kelly", 21),
      User("Mike", 16),
      User("Mike", 43),
    )
  }
}
