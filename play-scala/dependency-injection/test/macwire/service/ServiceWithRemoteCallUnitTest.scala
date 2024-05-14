package macwire.service

import macwire.components.{ApiComponents, MockApiComponents, ServiceComponents}
import org.scalatest.wordspec.AnyWordSpec

class ServiceWithRemoteCallUnitTest extends AnyWordSpec {

  "ServiceWithRemoteCall call" should {

    "invoke mock when remote api is mocked" in new ServiceComponents
      with MockApiComponents {
      assert(serviceWithRemoteCall.call() == "Mock remote api call")
    }

    "invoke real method when real api is wired" in new ServiceComponents
      with ApiComponents {
      assert(serviceWithRemoteCall.call() == "Real remote api call")
    }

  }

}
