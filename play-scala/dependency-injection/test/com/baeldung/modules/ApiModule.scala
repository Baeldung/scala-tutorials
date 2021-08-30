package com.baeldung.modules

import com.google.inject.AbstractModule

trait RemoteApi {
  def remoteCall(): String
}

class RealRemoteApi extends RemoteApi {
  override def remoteCall(): String = "Real remote api call"
}

class MockRemoteApi extends RemoteApi {
  override def remoteCall(): String = "Mock remote api call"
}

class ApiModule extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[RemoteApi])
      .toInstance(new RealRemoteApi)
  }

}

class MockApiModule extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[RemoteApi])
      .toInstance(new MockRemoteApi)
  }

}
