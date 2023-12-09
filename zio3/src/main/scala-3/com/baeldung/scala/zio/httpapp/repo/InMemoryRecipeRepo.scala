package com.baeldung.scala.zio.httpapp.repo

import com.baeldung.scala.zio.httpapp.app.Recipe
import zio.{Ref, Task, UIO, ZLayer}

case class InMemoryRecipeRepo(map: Ref[Map[Long, Recipe]]) extends RecipeRepo:
  def save(recipe: Recipe): UIO[Recipe] =
    for _ <- map.update(_ + (recipe.id -> recipe))
    yield recipe

  def find(id: Long): UIO[Option[Recipe]] =
    map.get.map(_.get(id))

  def update(recipe: Recipe): Task[Option[Recipe]] =
    for
      _ <- map.update(_ + (recipe.id -> recipe))
      recipeOpt <- map.get.map(_.get(recipe.id))
    yield recipeOpt

  def delete(id: Long): Task[Option[Recipe]] =
    for
      recipe <- map.get.map(_.get(id))
      _ <- map.update(_ - id)
    yield recipe

object InMemoryRecipeRepo {
  def layer: ZLayer[Any, Nothing, RecipeRepo] =
    ZLayer.fromZIO(
      Ref.make(Map.empty[Long, Recipe]).map(new InMemoryRecipeRepo(_))
    )
}
