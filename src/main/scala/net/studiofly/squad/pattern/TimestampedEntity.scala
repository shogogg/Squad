package net.studiofly.squad.pattern

import java.sql.Timestamp

trait TimestampedEntity {
  var ctime: Timestamp = null
  var mtime: Timestamp = null
}
