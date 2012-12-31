Squad
=====
Squeryl additional DSL, patterns and utilities.

Features
--------
- Squad provides additional DSL with [Squeryl](http://squeryl.org/getting-started.html).
- Squad provides database modeling patterns.
- Squad provides utilities.


Additional DSL
--------------
### JOIN
Supports `innerJoin`, `leftJoin`, `rightJoin` and `fullJoin` in from keyword.

in Squeryl
```scala
import org.squeryl.PrimitiveTypeMode._

val ratingsForAllSongs =
  join(songs, ratings.leftOuter)((s,r) =>
      select(s, r)
      on(s.id === r.map(_.songId))
  )
```
with Squad
```scala
import net.studiofly.squad.SquadMode._

val ratingsForAllSongs =
  from(songs leftJoin ratings)((s,r) =>
      select(s, r)
      on(s.id === r.map(_.songId))
  )
```
Otherwise, joined tables have `find` method.
```scala
import net.studiofly.squad.SquadMode._

val ratingsForAllSongs =
  songs.leftJoin(ratings).find((s,r) =>
      select(s, r)
      on(s.id === r.map(_.songId))
  )
```


Database modeling patterns
--------------------------
### LongKeyedEntity
in Squeryl
```scala
class Entity extends KeyedEntity[Long] {
  val id: Long = 0
  val name: String
}
```
with Squad
```scala
class Entity extends LongKeyedEntity {
  val name: String
}
```

### TimestampedEntity
TimestampedEntity has fields `ctime` and `mtime`. `ctime` will set current timestamp on insert. `mtime` will set current timestamp on insert, and auto update on update.


Utilities
---------
### currentTimestamp
```scala
import java.sql.Timestamp

val timestamp = new Timestamp(System.currentTimeMillis)
```
#### with Squad
```scala
import java.sql.Timestamp
import net.studiofly.squad.SquadMode._

val timestamp = currentTimestamp
```
