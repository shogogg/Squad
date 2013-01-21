package net.studiofly.squad.musicdb

import net.studiofly.squad.SquadMode._
import net.studiofly.squad._
import net.studiofly.squad.pattern.LongKeyedEntity
import org.squeryl.KeyedEntity
import org.squeryl.dsl.CompositeKey2

object MusicDb extends SquadSchema {
  val band = table[Band]
  val person = table[Person]
  val song = table[Song]

  /** Band to person relation (ManyToMany) */
  val bandMember = manyToManyRelation(band, person)
    .via[BandMember]((b, p, bm) => (bm.bandId === b.id, bm.personId === p.id))
  bandMember.leftForeignKeyDeclaration.constrainReference(onDelete.cascade)

  /** Band to song relation (OneToMany) */
  val bandToSong = oneToManyRelation(band, song).via((b, s) => (s.bandId === b.id))
  bandToSong.foreignKeyDeclaration.constrainReference(onDelete.cascade)
}

case class Band(var name: String) extends LongKeyedEntity {
  import MusicDb._
  lazy val members = bandMember.left(this)
  lazy val songs = bandToSong.left(this)
}

case class Person(var firstName: String, var lastName: String, var age: Int) extends LongKeyedEntity {
  import MusicDb._
  lazy val bands = bandMember.right(this)
}

case class Song(var title: String) extends LongKeyedEntity {
  import MusicDb._
  val bandId: Long = 0
  lazy val band = bandToSong.right(this)
}

class BandMember(
  val bandId: Long,
  val personId: Long
  ) extends KeyedEntity[CompositeKey2[Long, Long]] {

  def id = new CompositeKey2(bandId, personId)
  def band = MusicDb.band.lookup(bandId)
  def person = MusicDb.person.lookup(personId)
}
