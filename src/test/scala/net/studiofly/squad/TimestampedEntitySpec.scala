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
