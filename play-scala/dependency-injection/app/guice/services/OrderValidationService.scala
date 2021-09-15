package guice.services

import models.Order

trait OrderValidationService {
  def validate(order: Order): Boolean
}

class BusinessOrderValidationService extends OrderValidationService {
  override def validate(order: Order): Boolean = {
    println("Business order validation")
    true
  }
}

class EnterpriseOrderValidationService extends OrderValidationService {
  override def validate(order: Order): Boolean = {
    println("Enterprise order validation")
    true
  }
}
