package com.baeldung.services

import com.baeldung.models.Order

trait OrderValidationService {
  def validate(order: Order): Boolean
}

trait Business
trait Enterprise

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
