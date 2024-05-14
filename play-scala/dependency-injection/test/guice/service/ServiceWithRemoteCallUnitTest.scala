package guice.service

import guice.modules.{
  ApiModule,
  MockApiModule,
  ServiceModule,
  ServiceWithRemoteCall
}
import org.scalatest.wordspec.FixtureAnyWordSpecLike
import org.scalatestplus.play.MixedFixtures
import play.api.inject.guice.GuiceApplicationBuilder

class ServiceWithRemoteCallUnitTest
  extends MixedFixtures
  with FixtureAnyWordSpecLike {

  "ServiceWithRemoteCall call" should {
    "invoke mock when remote api is mocked" in {
      val application = new GuiceApplicationBuilder()
        .overrides(new MockApiModule, new ServiceModule)
        .build()
      new App(application) {
        val srv = app.injector.instanceOf[ServiceWithRemoteCall]
        assert(srv.call() == "Mock remote api call")
      }
    }

    "invoke real method when real api is wired" in {
      val application = new GuiceApplicationBuilder()
        .overrides(new ApiModule, new ServiceModule)
        .build()
      new App(application) {
        val srv = app.injector.instanceOf[ServiceWithRemoteCall]
        assert(srv.call() == "Real remote api call")
      }
    }

  }

}
