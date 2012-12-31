package net.studiofly.squad.dsl

import net.studiofly.squad.queryable._

trait SquadFromSignature {
  def from[A,R](q: SquadQueryable1[A]) = q.find[R]
  def from[A,B,R](q: SquadQueryable2[A,B]) = q.find[R]
  def from[A,B,C,R](q: SquadQueryable3[A,B,C]) = q.find[R]
  def from[A,B,C,D,R](q: SquadQueryable4[A,B,C,D]) = q.find[R]
  def from[A,B,C,D,E,R](q: SquadQueryable5[A,B,C,D,E]) = q.find[R]
  def from[A,B,C,D,E,F,R](q: SquadQueryable6[A,B,C,D,E,F]) = q.find[R]
  def from[A,B,C,D,E,F,G,R](q: SquadQueryable7[A,B,C,D,E,F,G]) = q.find[R]
  def from[A,B,C,D,E,F,G,H,R](q: SquadQueryable8[A,B,C,D,E,F,G,H]) = q.find[R]
}
