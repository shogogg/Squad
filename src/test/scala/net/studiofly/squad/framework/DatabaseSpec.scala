package net.studiofly.squad.framework

import java.sql.DriverManager
import org.specs2.mutable._
import org.squeryl.{Session, SessionFactory}
import org.squeryl.adapters.H2Adapter
import net.studiofly.squad.SquadMode._
import net.studiofly.squad.SquadSchema
import org.specs2.matcher.MatchResult

trait DatabaseSpec extends Specification {
  val database: SquadSchema

  /**
   * Create session factory.
   */
  def conn = {
    Class.forName("org.h2.Driver")
    DriverManager.getConnection("jdbc:h2:mem:squad-test")
  }
  SessionFactory.concreteFactory = Some(() => Session.create(conn, new H2Adapter))

  /**
   * Test with test database.
   */
  def withTestDatabase[A](f: => A): A = {
    inTransaction {
      database.drop
      database.create
      f
    }
  }
}
