package com.baeldung.arrival.controller

import com.baeldung.arrival.service.ArrivalService
import play.api.libs.json.Json
import play.api.mvc.{BaseController, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class ArrivalController @Inject()(arrivalService: ArrivalService, val controllerComponents: ControllerComponents)(implicit ec: ExecutionContext) extends BaseController {

  def index() = Action.async { _ =>
    arrivalService.getArrivals().map(arrivals => Ok(Json.toJson(arrivals)))
  }

}
