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
package net.studiofly.squad

import net.studiofly.squad.framework.DatabaseSpec
import net.studiofly.squad.pattern.{LongKeyedEntity, TimestampedEntity}

class TimestampedEntitySpec extends DatabaseSpec {
  "TimestampedEntity" should {
    "update timestamps automaticaly on insert/update" >> {
      withTestDatabase {
        val a = new TestEntity("John")
        database.testEntity.insert(a)
        val c = a.ctime
        val m = a.mtime
        c must_!= null
        m must_!= null
        Thread.sleep(10)
        a.name = "Smith"
        database.testEntity.update(a)
        a.ctime must_== c
        a.mtime must_!= m
      }
    }
  }
  val database = new TestSchema
}
class TestEntity(var name: String) extends LongKeyedEntity with TimestampedEntity
class TestSchema extends SquadSchema {
  val testEntity = table[TestEntity]
}
