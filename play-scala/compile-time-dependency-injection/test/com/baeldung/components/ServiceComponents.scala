package com.baeldung.components

import com.softwaremill.macwire._

class ServiceWithRemoteCall(remoteApi: RemoteApi) {
  def call(): String = remoteApi.remoteCall()
}

trait ServiceComponents {

  self: ApiComponents =>

  lazy val serviceWithRemoteCall: ServiceWithRemoteCall = wire[ServiceWithRemoteCall]

}
