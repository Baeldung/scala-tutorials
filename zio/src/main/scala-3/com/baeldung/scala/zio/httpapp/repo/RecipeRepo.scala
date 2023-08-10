package com.baeldung.scala.zio.httpapp.repo

import com.baeldung.scala.zio.httpapp.app.Recipe
import zio._

trait RecipeRepo:
  def save(recipe: Recipe): Task[Recipe]

  def find(id: Long): Task[Option[Recipe]]

  def update(recipe: Recipe): Task[Option[Recipe]]

  def delete(id: Long): Task[Option[Recipe]]
