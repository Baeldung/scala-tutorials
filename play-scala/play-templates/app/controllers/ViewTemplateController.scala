package controllers

import com.google.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

case class Article(title: String, url: String)

class ViewTemplateController @Inject() (cc: ControllerComponents)
  extends AbstractController(cc) {

  def index = Action {
    val articles = List(
      (
        "Introduction to Play Framework",
        "https://www.baeldung.com/scala/play-framework-intro"
      ),
      (
        "Building REST API in Scala with Play Framework",
        "https://www.baeldung.com/scala/play-rest-api"
      )
    )
    Ok(views.html.Baeldung.index(articles))
  }

  def withClass = Action {
    val articles = List(
      Article(
        "Introduction to Play Framework",
        "https://www.baeldung.com/scala/play-framework-intro"
      ),
      Article(
        "Building REST API in Scala with Play Framework",
        "https://www.baeldung.com/scala/play-rest-api"
      )
    )
    Ok(views.html.Baeldung.with_class(articles))
  }
}
