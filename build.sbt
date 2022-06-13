val scala3Version = "3.1.2"

name := "bingjvm"
organization := "net.mem-memov"
version := "0.1.1-SNAPSHOT"
licenses := Seq("APL2" -> url("https://www.apache.org/licenses/LICENSE-2.0.txt"))
description := "A library creating in-memory address databases"
import xerial.sbt.Sonatype._
sonatypeProjectHosting := Some(GitHubHosting("mem-memov", "bingjvm", "mem-memov@yandex.ru"))
publishTo := sonatypePublishToBundle.value
scalaVersion := scala3Version
ThisBuild / versionScheme := Some("early-semver")

// documentation

enablePlugins(ParadoxPlugin, ParadoxSitePlugin)
paradoxTheme := Some(builtinParadoxTheme("generic"))
//Paradox / sourceDirectory := sourceDirectory.value / "documentation"

libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test


// https://docs.scala-lang.org/overviews/contributors/index.html
