package com.baeldung.components

trait RemoteApi {
  def remoteCall(): String
}

class RealRemoteApi extends RemoteApi {
  override def remoteCall(): String = "Real remote api call"
}

class MockRemoteApi extends RemoteApi {
  override def remoteCall(): String = "Mock remote api call"
}

trait ApiComponents {
  lazy val remoteApi: RemoteApi = new RealRemoteApi
}

trait MockApiComponents extends ApiComponents {
  override lazy val remoteApi: RemoteApi = new MockRemoteApi
}
