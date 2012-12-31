package net.studiofly.squad.framework

import net.studiofly.squad.SquadMode
import org.squeryl.{Query,Queryable}
import org.squeryl.dsl.QueryYield
import org.squeryl.dsl.internal.JoinedQueryable
import org.squeryl.dsl.boilerplate._

private[squad] object MockMode extends SquadMode {
  val mockMsg1 = "from 1 table"
  val mockMsg2 = "join 2 tables"
  val mockMsg3 = "join 3 tables"
  val mockMsg4 = "join 4 tables"
  val mockMsg5 = "join 5 tables"
  val mockMsg6 = "join 6 tables"
  val mockMsg7 = "join 7 tables"
  val mockMsg8 = "join 8 tables"
  val mockMsg9 = "join 9 tables"
  val mockMsg10 = "join 10 tables"

  /** Throw Exception when called `from` */
  override def from[T1,R](t1:Queryable[T1])(f: (T1) => QueryYield[R]): Query[R] =
    throw new Exception(mockMsg1)

  /** Throw Exception when called `join` */
  override def join[A,B1,C](
    q: Queryable[A],
    q1: JoinedQueryable[B1])(
    f: (A,B1) => JoinQueryYield1[C]): Query[C] = throw new Exception(mockMsg2)
  override def join[A,B1,B2,C](
    q: Queryable[A],
    q1: JoinedQueryable[B1],
    q2: JoinedQueryable[B2])(
    f: (A,B1,B2) => JoinQueryYield2[C]): Query[C] = throw new Exception(mockMsg3)
  override def join[A,B1,B2,B3,C](
    q: Queryable[A],
    q1: JoinedQueryable[B1],
    q2: JoinedQueryable[B2],
    q3: JoinedQueryable[B3])(
    f: (A,B1,B2,B3) => JoinQueryYield3[C]): Query[C] = throw new Exception(mockMsg4)
  override def join[A,B1,B2,B3,B4,C](
    q: Queryable[A],
    q1: JoinedQueryable[B1],
    q2: JoinedQueryable[B2],
    q3: JoinedQueryable[B3],
    q4: JoinedQueryable[B4])(
    f: (A,B1,B2,B3,B4) => JoinQueryYield4[C]): Query[C] = throw new Exception(mockMsg5)
  override def join[A,B1,B2,B3,B4,B5,C](
    q: Queryable[A],
    q1: JoinedQueryable[B1],
    q2: JoinedQueryable[B2],
    q3: JoinedQueryable[B3],
    q4: JoinedQueryable[B4],
    q5: JoinedQueryable[B5])(
    f: (A,B1,B2,B3,B4,B5) => JoinQueryYield5[C]): Query[C] = throw new Exception(mockMsg6)
  override def join[A,B1,B2,B3,B4,B5,B6,C](
    q: Queryable[A],
    q1: JoinedQueryable[B1],
    q2: JoinedQueryable[B2],
    q3: JoinedQueryable[B3],
    q4: JoinedQueryable[B4],
    q5: JoinedQueryable[B5],
    q6: JoinedQueryable[B6])(
    f: (A,B1,B2,B3,B4,B5,B6) => JoinQueryYield6[C]): Query[C] = throw new Exception(mockMsg7)
  override def join[A,B1,B2,B3,B4,B5,B6,B7,C](
    q: Queryable[A],
    q1: JoinedQueryable[B1],
    q2: JoinedQueryable[B2],
    q3: JoinedQueryable[B3],
    q4: JoinedQueryable[B4],
    q5: JoinedQueryable[B5],
    q6: JoinedQueryable[B6],
    q7: JoinedQueryable[B7])(
    f: (A,B1,B2,B3,B4,B5,B6,B7) => JoinQueryYield7[C]): Query[C] = throw new Exception(mockMsg8)
  override def join[A,B1,B2,B3,B4,B5,B6,B7,B8,C](
    q: Queryable[A],
    q1: JoinedQueryable[B1],
    q2: JoinedQueryable[B2],
    q3: JoinedQueryable[B3],
    q4: JoinedQueryable[B4],
    q5: JoinedQueryable[B5],
    q6: JoinedQueryable[B6],
    q7: JoinedQueryable[B7],
    q8: JoinedQueryable[B8])(
    f: (A,B1,B2,B3,B4,B5,B6,B7,B8) => JoinQueryYield8[C]): Query[C] = throw new Exception(mockMsg9)
  override def join[A,B1,B2,B3,B4,B5,B6,B7,B8,B9,C](
    q: Queryable[A],
    q1: JoinedQueryable[B1],
    q2: JoinedQueryable[B2],
    q3: JoinedQueryable[B3],
    q4: JoinedQueryable[B4],
    q5: JoinedQueryable[B5],
    q6: JoinedQueryable[B6],
    q7: JoinedQueryable[B7],
    q8: JoinedQueryable[B8],
    q9: JoinedQueryable[B9])(
    f: (A,B1,B2,B3,B4,B5,B6,B7,B8,B9) => JoinQueryYield9[C]): Query[C] = throw new Exception(mockMsg10)
}