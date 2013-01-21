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
