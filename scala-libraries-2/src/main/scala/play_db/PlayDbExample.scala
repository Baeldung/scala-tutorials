package play_db

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.{Inject,Singleton}
import scala.concurrent.{ExecutionContext, Future}

object PlayDbExample {

  @Singleton 
  class UserDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) 
    extends HasDatabaseConfigProvider[JdbcProfile] { 
    import profile.api._

    def findEmployeeNameById(id : Int) : Future[Option[String]] = { 
      val query = sql"select name from employees where id = ${id};".as[String] 
      val queryResult : Future[Vector[String]] = db.run(query) 
      queryResult.map(_.headOption) 
    }
    
  }

}
