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
