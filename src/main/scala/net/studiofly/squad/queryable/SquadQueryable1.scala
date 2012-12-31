package net.studiofly.squad.queryable

import org.squeryl.Queryable
import org.squeryl.dsl.QueryDsl
import net.studiofly.squad.SquadMode._

private[squad] class SquadQueryable1[A](dsl: QueryDsl)(a: Queryable[A]) extends SquadQueryable {
  /** Select from table. */
  final def find[R] = dsl.from[A,R](a) _

  /** Full outer join */
  final def fullJoin[X](x: Queryable[X]) =
    new SquadQueryable2[A, Option[X]](dsl)(a, x.fullOuter)

  /** Left outer join */
  final def leftJoin[X](x: Queryable[X]) =
    new SquadQueryable2[A, Option[X]](dsl)(a, x.leftOuter)

  /** Right outer join */
  final def rightJoin[X](x: Queryable[X]) =
    new SquadQueryable2[A, Option[X]](dsl)(a, x.rightOuter)

  /** Inner join */
  final def innerJoin[X](x: Queryable[X]) =
    new SquadQueryable2[A,X](dsl)(a, x)
}
