package net.studiofly.squad.queryable

import org.squeryl.Queryable
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.dsl.QueryDsl
import org.squeryl.dsl.internal.JoinedQueryable

private[squad] class SquadQueryable5[A, B, C, D, E](dsl: QueryDsl)(
  a: Queryable[A],
  b: JoinedQueryable[B],
  c: JoinedQueryable[C],
  d: JoinedQueryable[D],
  e: JoinedQueryable[E]) extends SquadQueryable {

  /** Select from joined tables. */
  final def find[R] = dsl.join[A, B, C, D, E, R](a, b, c, d, e) _

  /** Full outer join */
  final def fullJoin[X](x: Queryable[X]) =
    new SquadQueryable6[A, B, C, D, E, Option[X]](dsl)(a, b, c, d, e, x.fullOuter)

  /** Left outer join */
  final def leftJoin[X](x: Queryable[X]) =
    new SquadQueryable6[A, B, C, D, E, Option[X]](dsl)(a, b, c, d, e, x.leftOuter)

  /** Right outer join */
  final def rightJoin[X](x: Queryable[X]) =
    new SquadQueryable6[A, B, C, D, E, Option[X]](dsl)(a, b, c, d, e, x.rightOuter)

  /** Inner join */
  final def innerJoin[X](x: Queryable[X]) =
    new SquadQueryable6[A, B, C, D, E, X](dsl)(a, b, c, d, e, x)
}
