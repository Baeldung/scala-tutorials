package com.baeldung.gatling

import io.gatling.core.Predef.{details, _}
import com.baeldung.gatling.ChainRequestsProvider.simpleRequest
import com.baeldung.gatling.ScenariosProvider.getScenario

class PeakLoadSimulation extends Simulation {

  setUp(
    getScenario(
      "getExistingEndpoint",
      simpleRequest("request_status_endpoint", "/health/status", 200),
      50,
      10,
      60
    ),
    getScenario(
      "nonExistingEndpoint",
      simpleRequest("request_wrong_endpoint", "/health/status1", 200),
      5,
      10,
      60
    )
  ).assertions(
    details("request_status_endpoint").successfulRequests.percent.gt(99.99),
    details("request_status_endpoint").responseTime.percentile4.lt(20),
    details("request_status_endpoint").requestsPerSec.gt(40),
    details("request_wrong_endpoint").successfulRequests.percent.lt(1),
    details("request_wrong_endpoint").responseTime.percentile4.lt(20)
  )
}
