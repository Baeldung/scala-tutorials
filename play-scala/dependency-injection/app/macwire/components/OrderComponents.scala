package macwire.components

import com.softwaremill.macwire.{wire, wireSet}
import com.softwaremill.tagging._
import models.Order
import macwire.services.{Business, BusinessOrderValidationService, Enterprise, EnterpriseOrderValidationService, OrderPipelineProcessor, OrderService}

trait OrderComponents extends {

  lazy val p1: OrderPipelineProcessor = (order: Order) =>
    println("Processor 1 processed")
  lazy val p2: OrderPipelineProcessor = (order: Order) =>
    println("Processor 2 processed")
  lazy val p3: OrderPipelineProcessor = (order: Order) =>
    println("Processor 3 processed")
  lazy val p4: OrderPipelineProcessor = (order: Order) =>
    println("Processor 4 processed")

  lazy val orderPipelineProcessors = wireSet[OrderPipelineProcessor]

  lazy val businessOrderValidationService
    : BusinessOrderValidationService @@ Business =
    (new BusinessOrderValidationService).taggedWith[Business]
  lazy val enterpriseOrderValidationService
    : EnterpriseOrderValidationService @@ Enterprise =
    (new EnterpriseOrderValidationService).taggedWith[Enterprise]

  lazy val orderService = wire[OrderService]

}
