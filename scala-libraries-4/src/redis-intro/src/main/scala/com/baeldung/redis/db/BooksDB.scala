package com.baeldung.redis.db

object BooksDB {

  private val author1 = Author(1L, "James Brown")
  private val author2 = Author(2L, "Bob White")
  private val author3 = Author(3L, "Hannah Robertson")

  def authors: List[Author] = {
    List(
      author1,
      author2,
      author3
    )
  }

  def books: List[Book] = {
    List(
      Book(1L, author1.id, "Title 1"),
      Book(2L, author2.id, "Title 2"),
      Book(3L, author3.id, "Title 3"),
      Book(4L, author1.id, "Title 5"),
      Book(5L, author2.id, "Title 6"),
      Book(6L, author3.id, "Title 7"),
      Book(7L, author1.id, "Title 9"),
      Book(8L, author2.id, "Title 10")
    )
  }

}
