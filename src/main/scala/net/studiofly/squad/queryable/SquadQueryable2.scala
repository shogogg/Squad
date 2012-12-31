package net.studiofly.squad.queryable

import org.squeryl.Queryable
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.dsl.QueryDsl
import org.squeryl.dsl.internal.JoinedQueryable

private[squad] class SquadQueryable2[A,B](dsl: QueryDsl)(
  a: Queryable[A],
  b: JoinedQueryable[B]) extends SquadQueryable {

  /** Select from joined tables. */
  final def find[R] = dsl.join[A,B,R](a, b) _

  /** Full outer join */
  final def fullJoin[X](x: Queryable[X]) =
    new SquadQueryable3[A,B,Option[X]](dsl)(a, b, x.fullOuter)

  /** Left outer join */
  final def leftJoin[X](x: Queryable[X]) =
    new SquadQueryable3[A,B,Option[X]](dsl)(a, b, x.leftOuter)

  /** Right outer join */
  final def rightJoin[X](x: Queryable[X]) =
    new SquadQueryable3[A,B,Option[X]](dsl)(a, b, x.rightOuter)

  /** Inner join */
  final def innerJoin[X](x: Queryable[X]) =
    new SquadQueryable3[A,B,X](dsl)(a, b, x)
}
