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
package net.studiofly.squad

import scala.language.implicitConversions
import net.studiofly.squad.dsl.SquadDsl
import net.studiofly.squad.queryable.SquadQueryable1
import org.squeryl.{PrimitiveTypeMode, Queryable}

trait SquadMode extends PrimitiveTypeMode with SquadDsl with Util {
  /**
   * Implicit conversion: Queryable[T] to SquadQueryable1[T]
   */
  implicit def queryableToSquadQueryable[T](q: Queryable[T]) = new SquadQueryable1[T](this)(q)
}
object SquadMode extends SquadMode
