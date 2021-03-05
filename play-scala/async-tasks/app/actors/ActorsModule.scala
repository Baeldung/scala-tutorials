package actors

import com.google.inject.AbstractModule
import play.libs.akka.AkkaGuiceSupport

class ActorsModule extends AbstractModule with AkkaGuiceSupport {
  override def configure(): Unit = {
    bindActor(classOf[AsyncTaskInActor], "async-job-actor")
  }
}
