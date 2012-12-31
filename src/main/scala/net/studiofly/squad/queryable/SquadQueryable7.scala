package net.studiofly.squad.queryable

import org.squeryl.Queryable
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.dsl.QueryDsl
import org.squeryl.dsl.internal.JoinedQueryable

private[squad] class SquadQueryable7[A, B, C, D, E, F, G](dsl: QueryDsl)(
  a: Queryable[A],
  b: JoinedQueryable[B],
  c: JoinedQueryable[C],
  d: JoinedQueryable[D],
  e: JoinedQueryable[E],
  f: JoinedQueryable[F],
  g: JoinedQueryable[G]) extends SquadQueryable {

  /** Select from joined tables. */
  final def find[R] = dsl.join[A, B, C, D, E, F, G, R](a, b, c, d, e, f, g) _

  /** Full outer join */
  final def fullJoin[X](x: Queryable[X]) =
    new SquadQueryable8[A, B, C, D, E, F, G, Option[X]](dsl)(a, b, c, d, e, f, g, x.fullOuter)

  /** Left outer join */
  final def leftJoin[X](x: Queryable[X]) =
    new SquadQueryable8[A, B, C, D, E, F, G, Option[X]](dsl)(a, b, c, d, e, f, g, x.leftOuter)

  /** Right outer join */
  final def rightJoin[X](x: Queryable[X]) =
    new SquadQueryable8[A, B, C, D, E, F, G, Option[X]](dsl)(a, b, c, d, e, f, g, x.rightOuter)

  /** Inner join */
  final def innerJoin[X](x: Queryable[X]) =
    new SquadQueryable8[A, B, C, D, E, F, G, X](dsl)(a, b, c, d, e, f, g, x)
}
