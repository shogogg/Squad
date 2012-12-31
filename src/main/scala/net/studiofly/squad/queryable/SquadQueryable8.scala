package net.studiofly.squad.queryable

import org.squeryl.Queryable
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.dsl.QueryDsl
import org.squeryl.dsl.internal.JoinedQueryable

private[squad] class SquadQueryable8[A,B,C,D,E,F,G,H](dsl: QueryDsl)(
  a: Queryable[A],
  b: JoinedQueryable[B],
  c: JoinedQueryable[C],
  d: JoinedQueryable[D],
  e: JoinedQueryable[E],
  f: JoinedQueryable[F],
  g: JoinedQueryable[G],
  h: JoinedQueryable[H]) extends SquadQueryable {

  /** Select from joined tables. */
  final def find[R] = dsl.join[A,B,C,D,E,F,G,H,R](a, b, c, d, e, f, g, h) _

  /** Full outer join */
  final def fullJoin[X](x: Queryable[X]): SquadQueryable =
    throw new UnsupportedOperationException("SquadQueryable8.fullJoin not supported.")

  /** Left outer join */
  final def leftJoin[X](x: Queryable[X]): SquadQueryable =
    throw new UnsupportedOperationException("SquadQueryable8.leftJoin not supported.")

  /** Right outer join */
  final def rightJoin[X](x: Queryable[X]): SquadQueryable =
    throw new UnsupportedOperationException("SquadQueryable8.rightJoin not supported.")

  /** Inner join */
  final def innerJoin[X](x: Queryable[X]): SquadQueryable =
    throw new UnsupportedOperationException("SquadQueryable8.innerJoin not supported.")
}
