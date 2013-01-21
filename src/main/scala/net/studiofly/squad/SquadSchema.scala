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

import scala.collection.mutable.ArrayBuffer
import org.squeryl.Schema
import org.squeryl.internals.LifecycleEvent
import net.studiofly.squad.pattern.TimestampedEntity

trait SquadSchema extends Schema {
  /** Import function "snakify" */
  import NamingConventionTransforms.snakify

  /**
   * Convert to database column name from model property name.
   * You can override this method if you have to change the rule.
   */
  override def columnNameFromPropertyName(propertyName: String) = snakify(propertyName)

  /**
   * Convert to database table name from model class name.
   * You can override this method if you have to change the rule.
   */
  override def tableNameFromClassName(tableName: String) = snakify(tableName)

  /**
   * Lifecycle Callbacks
   */
  override def callbacks: Seq[LifecycleEvent] = {
    setupCallbacks()
    callbackArrayBuffer.toSeq
  }
  private val callbackArrayBuffer = new ArrayBuffer[LifecycleEvent]

  /**
   * Add lifecycle callback
   */
  protected final def addCallback(cb: LifecycleEvent) {
    callbackArrayBuffer.append(cb)
  }

  /**
   * Setup callbacks
   */
  protected def setupCallbacks() {
    addCallback(beforeInsert[TimestampedEntity].call { (a) =>
      val now = Util.currentTimestamp
      a.ctime = now
      a.mtime = now
    })
    addCallback(beforeUpdate[TimestampedEntity].call { (a) =>
      a.mtime = Util.currentTimestamp
    })
  }
}
