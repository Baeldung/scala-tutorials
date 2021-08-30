package com.baeldung.services

import com.baeldung.models.Order
import com.softwaremill.tagging._
import play.api.Logger

class OrderService(
  businessOrderValidationService: OrderValidationService @@ Business,
  enterpriseOrderValidationService: OrderValidationService @@ Enterprise,
  orderPipeline: Set[OrderPipelineProcessor]
) {
  private val log = Logger(getClass)

  def process(order: Order): Unit = {
    val validationOutcome = if (order.isEnterprise) {
      enterpriseOrderValidationService.validate(order)
    } else {
      businessOrderValidationService.validate(order)
    }
    if (validationOutcome) {
      orderPipeline.foreach(_.process(order))
    } else {
      log.error("Invalid order found.")
      // invalid order processing...
    }
  }
}

trait OrderPipelineProcessor {
  def process(order: Order): Unit
}
