package net.studiofly.squad

import scala.language.implicitConversions
import net.studiofly.squad.dsl.SquadDsl
import net.studiofly.squad.queryable.SquadQueryable1
import org.squeryl.{PrimitiveTypeMode, Queryable}

trait SquadMode extends PrimitiveTypeMode with SquadDsl with Util {
  /**
   * Implicit conversion: Queryable[T] to SquadQueryable1[T]
   */
  implicit def queryableToSquadQueryable[T](q: Queryable[T]) = new SquadQueryable1[T](this)(q)
}
object SquadMode extends SquadMode
