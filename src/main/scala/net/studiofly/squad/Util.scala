package net.studiofly.squad

import java.sql.Timestamp

trait Util {
  def currentTimestamp = new Timestamp(System.currentTimeMillis())
}
object Util extends Util
