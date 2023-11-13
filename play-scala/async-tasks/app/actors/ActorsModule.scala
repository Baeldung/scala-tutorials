package actors

import com.google.inject.AbstractModule
import play.libs.pekko.PekkoGuiceSupport

class ActorsModule extends AbstractModule with PekkoGuiceSupport {
  override def configure(): Unit = {
    bindActor(classOf[AsyncTaskInActor], "async-job-actor")
  }
}
