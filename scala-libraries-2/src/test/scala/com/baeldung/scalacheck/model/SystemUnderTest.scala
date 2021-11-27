package com.baeldung.scalacheck.model

import java.util.UUID

case class SystemUnderTest(refId: UUID, date: Long, trafficLight: TrafficLight)
