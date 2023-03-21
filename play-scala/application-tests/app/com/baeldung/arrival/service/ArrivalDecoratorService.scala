package com.baeldung.arrival.service

import com.baeldung.arrival.db.repository.Arrival
import play.api.Configuration

import javax.inject.Inject

trait Size {
  def short: Boolean

  def medium: Boolean

  def long: Boolean
}

class ArrivalDecoratorService @Inject()(configuration: Configuration) {

  private val maximumShortNameLength = configuration.get[Int]("short-name-max")
  private val maximumMediumNameLength = configuration.get[Int]("medium-name-max")

  def decorate(undecorated: Arrival): Arrival with Size = new Arrival(undecorated.planeId, undecorated.origin, undecorated.destination, undecorated.plane) with Size {
    override def short: Boolean = undecorated.plane.length <= maximumShortNameLength

    override def medium: Boolean = undecorated.plane.length > maximumShortNameLength && undecorated.plane.length <= maximumMediumNameLength

    override def long: Boolean = undecorated.plane.length > maximumMediumNameLength
  }

}
