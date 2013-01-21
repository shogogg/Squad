import sbt._
import sbt.Keys._

object BuildSettings {
  val buildName = "Squad"
  val buildOrganization = "net.studiofly"
  val buildVersion = "0.1.1"
  val buildScalaVersion = "2.10.0"
  val buildJavaOptions = Seq(
    "-Xdebug",
    "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
  )
  val buildSettings = Project.defaultSettings ++ Seq(
    name := buildName,
    organization := buildOrganization,
    version := buildVersion,
    scalaVersion := buildScalaVersion
  )
}

object BuildDependencies {
  val squeryl = "org.squeryl" %% "squeryl" % "0.9.5-6"
  val specs2 = "org.specs2" %% "specs2" % "1.13" % "test"
  val h2 = "com.h2database" % "h2" % "1.3.170" % "test"
}

object SquadBuild extends Build {
  import BuildSettings._
  import BuildDependencies._
  lazy val squad = Project(
    id = "squad",
    base = file("."),
    settings = buildSettings ++ Seq(
      libraryDependencies ++= Seq(
        squeryl,
        specs2,
        h2
      )
    )
  )
}
