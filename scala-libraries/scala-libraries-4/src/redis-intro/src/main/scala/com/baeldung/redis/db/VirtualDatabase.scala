package com.baeldung.redis.db

case class Book(id: Long, authorId: Long, title: String)

case class Author(id: Long, name: String)

class VirtualDatabase {

  def authors(): List[Author] = BooksDB.authors

  def books(): List[Book] = BooksDB.books

}
