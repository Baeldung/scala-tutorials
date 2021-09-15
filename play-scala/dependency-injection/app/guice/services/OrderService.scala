package guice.services

import models.Order
import com.google.inject.name.Named
import play.api.Logger

import javax.inject.Inject

class OrderService @Inject() (
  @Named("Business") businessOrderValidationService: OrderValidationService,
  @Named("Enterprise") enterpriseOrderValidationService: OrderValidationService,
  orderPipeline: Seq[OrderPipelineProcessor]
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
