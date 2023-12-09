package com.baeldung.scala.zio.httpapp.service

import com.baeldung.scala.zio.httpapp.app.Recipe
import com.baeldung.scala.zio.httpapp.repo.RecipeRepo
import zio.{Ref, Task, ZLayer, ZIO}

case class RecipeServiceImpl(recipeRepo: RecipeRepo) extends RecipeService:

  def save(recipe: Recipe): Task[Recipe] = recipeRepo.save(recipe)

  def find(id: Long): Task[Option[Recipe]] = recipeRepo.find(id)

  def update(recipe: Recipe): Task[Option[Recipe]] = recipeRepo.update(recipe)

  def delete(id: Long): Task[Option[Recipe]] = recipeRepo.delete(id)

trait RecipeService:

  def save(recipe: Recipe): Task[Recipe]

  def find(id: Long): Task[Option[Recipe]]

  def update(recipe: Recipe): Task[Option[Recipe]]

  def delete(id: Long): Task[Option[Recipe]]

object RecipeService {
  def layer: ZLayer[RecipeRepo, Nothing, RecipeService] =
    ZLayer {
      for {
        recipeRepo <- ZIO.service[RecipeRepo]
      } yield RecipeServiceImpl(recipeRepo)
    }
}
