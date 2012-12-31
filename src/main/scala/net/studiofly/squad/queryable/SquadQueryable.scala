package net.studiofly.squad.queryable

import org.squeryl.{Query, Queryable}
import org.squeryl.dsl.QueryYield

trait SquadQueryable {
  def fullJoin[X](x: Queryable[X]): SquadQueryable
  def leftJoin[X](x: Queryable[X]): SquadQueryable
  def rightJoin[X](x: Queryable[X]): SquadQueryable
  def innerJoin[X](x: Queryable[X]): SquadQueryable
}
