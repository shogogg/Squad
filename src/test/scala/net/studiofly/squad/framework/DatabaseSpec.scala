/*******************************************************************************
 * Copyright 2013 Shogo Kawase
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************** */
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
