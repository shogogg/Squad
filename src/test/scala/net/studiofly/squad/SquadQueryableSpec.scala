package net.studiofly.squad

import net.studiofly.squad.musicdb.MusicDb._
import net.studiofly.squad.queryable._
import net.studiofly.squad.framework.MockMode
import org.squeryl.internals.StatementWriter
import org.specs2.mutable.Specification

class SquadQueryableSpec extends Specification {
  "SquadQueryable" should {
    "have method `find`" >> {
      import MockMode._
      val x = MockLogicalBoolean
      val (q1,q2,q3,q4,q5,q6,q7,q8) = innerJoinQueries
      q1.find((p) =>
        select(p)) must throwA[Exception](mockMsg1)
      q2.find((b,s) =>
        select(b,s).on(x)) must throwA[Exception](mockMsg2)
      q3.find((b,s1,s2) =>
        select(b,s1,s2).on(x,x)) must throwA[Exception](mockMsg3)
      q4.find((b,s1,s2,s3) =>
        select(b,s1,s2,s3).on(x,x,x)) must throwA[Exception](mockMsg4)
      q5.find((b,s1,s2,s3,s4) =>
        select(b,s1,s2,s3,s4).on(x,x,x,x)) must throwA[Exception](mockMsg5)
      q6.find((b,s1,s2,s3,s4,s5) =>
        select(b,s1,s2,s3,s4,s5).on(x,x,x,x,x)) must throwA[Exception](mockMsg6)
      q7.find((b,s1,s2,s3,s4,s5,s6) =>
        select(b,s1,s2,s3,s4,s5,s6).on(x,x,x,x,x,x)) must throwA[Exception](mockMsg7)
      q8.find((b,s1,s2,s3,s4,s5,s6,s7) =>
        select(b,s1,s2,s3,s4,s5,s6,s7).on(x,x,x,x,x,x,x)) must throwA[Exception](mockMsg8)
    }
    "used in from" >> {
      import MockMode._
      val x = MockLogicalBoolean
      val (q1,q2,q3,q4,q5,q6,q7,q8) = innerJoinQueries
      from(q1)((p) =>
        select(p)) must throwA[Exception](mockMsg1)
      from(q2)((b,s) =>
        select(b,s).on(x)) must throwA[Exception](mockMsg2)
      from(q3)((b,s1,s2) =>
        select(b,s1,s2).on(x,x)) must throwA[Exception](mockMsg3)
      from(q4)((b,s1,s2,s3) =>
        select(b,s1,s2,s3).on(x,x,x)) must throwA[Exception](mockMsg4)
      from(q5)((b,s1,s2,s3,s4) =>
        select(b,s1,s2,s3,s4).on(x,x,x,x)) must throwA[Exception](mockMsg5)
      from(q6)((b,s1,s2,s3,s4,s5) =>
        select(b,s1,s2,s3,s4,s5).on(x,x,x,x,x)) must throwA[Exception](mockMsg6)
      from(q7)((b,s1,s2,s3,s4,s5,s6) =>
        select(b,s1,s2,s3,s4,s5,s6).on(x,x,x,x,x,x)) must throwA[Exception](mockMsg7)
      from(q8)((b,s1,s2,s3,s4,s5,s6,s7) =>
        select(b,s1,s2,s3,s4,s5,s6,s7).on(x,x,x,x,x,x,x)) must throwA[Exception](mockMsg8)
    }
    "be joinable (FULL JOIN)"  >> beInstancesOfSquadQueryable(fullJoinQueries)
    "be joinable (LEFT JOIN)"  >> beInstancesOfSquadQueryable(leftJoinQueries)
    "be joinable (RIGHT JOIN)" >> beInstancesOfSquadQueryable(rightJoinQueries)
    "be joinable (INNER JOIN)" >> beInstancesOfSquadQueryable(innerJoinQueries)
  }
  "SquadQueryable8" should {
    "not support join" >> {
      val (_,_,_,_,_,_,_,q8) = innerJoinQueries
      q8.fullJoin(band) must throwA[UnsupportedOperationException]
      q8.leftJoin(band) must throwA[UnsupportedOperationException]
      q8.rightJoin(band) must throwA[UnsupportedOperationException]
      q8.innerJoin(band) must throwA[UnsupportedOperationException]
    }
  }
  type Q = SquadQueryable
  private def beInstancesOfSquadQueryable(q: (Q,Q,Q,Q,Q,Q,Q,Q)) = {
    val (q1,q2,q3,q4,q5,q6,q7,q8) = q
    q1 must beAnInstanceOf[SquadQueryable1[_]]
    q2 must beAnInstanceOf[SquadQueryable2[_,_]]
    q3 must beAnInstanceOf[SquadQueryable3[_,_,_]]
    q4 must beAnInstanceOf[SquadQueryable4[_,_,_,_]]
    q5 must beAnInstanceOf[SquadQueryable5[_,_,_,_,_]]
    q6 must beAnInstanceOf[SquadQueryable6[_,_,_,_,_,_]]
    q7 must beAnInstanceOf[SquadQueryable7[_,_,_,_,_,_,_]]
    q8 must beAnInstanceOf[SquadQueryable8[_,_,_,_,_,_,_,_]]
  }
  /**
   * MockLogicalBoolean:
   */
  private object MockLogicalBoolean extends org.squeryl.dsl.ast.LogicalBoolean {
    def doWrite(sw: StatementWriter) {}
  }
  def fullJoinQueries = {
    val q1 = MockMode.queryableToSquadQueryable(person)
    val q2 = q1.fullJoin(band)
    val q3 = q2.fullJoin(band)
    val q4 = q3.fullJoin(band)
    val q5 = q4.fullJoin(band)
    val q6 = q5.fullJoin(band)
    val q7 = q6.fullJoin(band)
    val q8 = q7.fullJoin(band)
    (q1,q2,q3,q4,q5,q6,q7,q8)
  }
  def leftJoinQueries = {
    val q1 = MockMode.queryableToSquadQueryable(band)
    val q2 = q1.leftJoin(song)
    val q3 = q2.leftJoin(song)
    val q4 = q3.leftJoin(song)
    val q5 = q4.leftJoin(song)
    val q6 = q5.leftJoin(song)
    val q7 = q6.leftJoin(song)
    val q8 = q7.leftJoin(song)
    (q1,q2,q3,q4,q5,q6,q7,q8)
  }
  def rightJoinQueries = {
    val q1 = MockMode.queryableToSquadQueryable(band)
    val q2 = q1.rightJoin(song)
    val q3 = q2.rightJoin(song)
    val q4 = q3.rightJoin(song)
    val q5 = q4.rightJoin(song)
    val q6 = q5.rightJoin(song)
    val q7 = q6.rightJoin(song)
    val q8 = q7.rightJoin(song)
    (q1,q2,q3,q4,q5,q6,q7,q8)
  }
  def innerJoinQueries = {
    val q1 = MockMode.queryableToSquadQueryable(band)
    val q2 = q1.innerJoin(song)
    val q3 = q2.innerJoin(song)
    val q4 = q3.innerJoin(song)
    val q5 = q4.innerJoin(song)
    val q6 = q5.innerJoin(song)
    val q7 = q6.innerJoin(song)
    val q8 = q7.innerJoin(song)
    (q1,q2,q3,q4,q5,q6,q7,q8)
  }
}

