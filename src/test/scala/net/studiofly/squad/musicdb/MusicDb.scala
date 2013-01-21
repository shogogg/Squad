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
