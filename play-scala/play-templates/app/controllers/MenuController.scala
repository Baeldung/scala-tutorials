package controllers

import com.google.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

case class Product(productName: String, price: Double, isAvailable: Boolean)

class MenuController @Inject()(template: views.html.Baeldung.menu, cc: ControllerComponents)
        extends AbstractController(cc) {

    def availableProducts = Action { implicit request =>
        val products = List(
            Product("coffee", 8.99, true),
            Product("cake", 12.00, true),
            Product("pancake", 3.00, false)
        )
        Ok(template(products))
    }
}
