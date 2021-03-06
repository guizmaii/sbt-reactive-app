import sbt.IO
import ReleaseTransformations._
import scala.collection.immutable.Seq

sbtPlugin := true

val Versions = new {
  val crossSbtVersions = Vector("0.13.17", "1.1.6")
  val nativePackager   = "1.3.2"
  val playJson         = "2.6.5"
  val scala            = "2.12.4"
  val scalaTest        = "3.0.1"
}

name := "sbt-reactive-app"
organization := "com.lightbend.rp"
organizationName := "Lightbend, Inc."
startYear := Some(2017)
licenses += ("Apache-2.0", new URL("https://www.apache.org/licenses/LICENSE-2.0.txt"))

scalaVersion in Global := Versions.scala
crossSbtVersions := Versions.crossSbtVersions
scalacOptions ++= Vector("-deprecation")

libraryDependencies ++= Vector(
  "com.typesafe.play" %% "play-json" % Versions.playJson,
  "org.scalatest"     %% "scalatest" % Versions.scalaTest % "test"
)

enablePlugins(AutomateHeaderPlugin)

sourceGenerators in Compile += Def.task {
  val versionFile = (sourceManaged in Compile).value / "ProgramVersion.scala"

  val versionSource =
    """|package com.lightbend.rp.sbtreactiveapp
       |
       |object ProgramVersion {
       |  val current = "%s"
       |}
    """.stripMargin.format(version.value)

  IO.write(versionFile, versionSource)

  Seq(versionFile)
}

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % Versions.nativePackager)

publishMavenStyle := true

homepage := Some(url("https://www.lightbend.com/"))
developers := List(
  Developer("lightbend", "Lightbend Contributors", "", url("https://github.com/lightbend/sbt-reactive-app"))
)
sonatypeProfileName := "com.lightbend.rp"
scmInfo := Some(ScmInfo(url("https://github.com/lightbend/sbt-reactive-app"), "git@github.com:lightbend/sbt-reactive-app.git"))
publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)
releasePublishArtifactsAction := PgpKeys.publishSigned.value
releaseCrossBuild := false
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  releaseStepCommandAndRemaining("^test"),
  releaseStepCommandAndRemaining("^scripted"),
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  releaseStepCommandAndRemaining("^publishSigned"),
  setNextVersion,
  commitNextVersion,
  pushChanges
)
