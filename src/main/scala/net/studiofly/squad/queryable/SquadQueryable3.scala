package net.studiofly.squad.queryable

import org.squeryl.Queryable
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.dsl.QueryDsl
import org.squeryl.dsl.internal.JoinedQueryable

private[squad] class SquadQueryable3[A,B,C](dsl: QueryDsl)(
  a: Queryable[A],
  b: JoinedQueryable[B],
  c: JoinedQueryable[C]) extends SquadQueryable {

  /** Select from joined tables. */
  final def find[R] = dsl.join[A,B,C,R](a, b, c) _

  /** Full outer join */
  final def fullJoin[X](x: Queryable[X]) =
    new SquadQueryable4[A,B,C,Option[X]](dsl)(a, b, c, x.fullOuter)

  /** Left outer join */
  final def leftJoin[X](x: Queryable[X]) =
    new SquadQueryable4[A,B,C,Option[X]](dsl)(a, b, c, x.leftOuter)

  /** Right outer join */
  final def rightJoin[X](x: Queryable[X]) =
    new SquadQueryable4[A,B,C,Option[X]](dsl)(a, b, c, x.rightOuter)

  /** Inner join */
  final def innerJoin[X](x: Queryable[X]) =
    new SquadQueryable4[A,B,C,X](dsl)(a, b, c, x)
}
