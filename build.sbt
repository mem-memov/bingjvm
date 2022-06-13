import scala.Predef.ArrowAssoc
import xerial.sbt.Sonatype._

val scala3Version = "3.1.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "bingjvm",
    organization := "net.mem-memov",
    version := "0.1.1-SNAPSHOT",
    licenses := Seq("APL2" -> url("https://www.apache.org/licenses/LICENSE-2.0.txt")),
    description := "A library creating in-memory address databases",
    sonatypeProjectHosting := Some(GitHubHosting("mem-memov", "bingjvm", "mem-memov@yandex.ru")),
    publishTo := sonatypePublishToBundle.value,
    scalaVersion := scala3Version,
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )

// https://docs.scala-lang.org/overviews/contributors/index.html
