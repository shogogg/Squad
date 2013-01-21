/*******************************************************************************
 * Copyright 2013 Shogo Kawase
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************** */
package net.studiofly.squad.queryable

import org.squeryl.Queryable
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.dsl.QueryDsl
import org.squeryl.dsl.internal.JoinedQueryable

private[squad] class SquadQueryable4[A,B,C,D](dsl: QueryDsl)(
  a: Queryable[A],
  b: JoinedQueryable[B],
  c: JoinedQueryable[C],
  d: JoinedQueryable[D]) extends SquadQueryable {

  /** Select from joined tables. */
  final def find[R] = dsl.join[A,B,C,D,R](a, b, c, d) _

  /** Full outer join */
  final def fullJoin[X](x: Queryable[X]) =
    new SquadQueryable5[A,B,C,D,Option[X]](dsl)(a, b, c, d, x.fullOuter)

  /** Left outer join */
  final def leftJoin[X](x: Queryable[X]) =
    new SquadQueryable5[A,B,C,D,Option[X]](dsl)(a, b, c, d, x.leftOuter)

  /** Right outer join */
  final def rightJoin[X](x: Queryable[X]) =
    new SquadQueryable5[A,B,C,D,Option[X]](dsl)(a, b, c, d, x.rightOuter)

  /** Inner join */
  final def innerJoin[X](x: Queryable[X]) =
    new SquadQueryable5[A,B,C,D,X](dsl)(a, b, c, d, x)
}
