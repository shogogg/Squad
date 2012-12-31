package net.studiofly.squad.pattern

import org.squeryl.KeyedEntity

trait LongKeyedEntity extends KeyedEntity[Long] {
  val id: Long = 0
}
