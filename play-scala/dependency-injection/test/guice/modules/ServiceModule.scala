package guice.modules

import com.google.inject.AbstractModule

import javax.inject.{Inject, Singleton}

class ServiceWithRemoteCall @Inject() (remoteApi: RemoteApi) {
  def call(): String = remoteApi.remoteCall()
}

class ServiceModule extends AbstractModule {

  override def configure(): Unit = {

    bind(classOf[ServiceWithRemoteCall])
      .in(classOf[Singleton])

  }

}
