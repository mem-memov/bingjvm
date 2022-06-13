import xerial.sbt.Sonatype._

val scala3Version = "3.1.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "bingjvm",
    description := "A library creating in-memory address databases",


    organization := "net.mem-memov",
    homepage := Some(url("https://github.com/mem-memov/bingjvm")),
    licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    developers := List(
      Developer(
        "mem-memov",
        "Aleksandr Novikov",
        "mem-memov@yandex.ru",
        url("http://mem-memov.net")
      )
    ),
    sonatypeCredentialHost := "s01.oss.sonatype.org",
    sonatypeRepository := "https://s01.oss.sonatype.org/service/local",
    versionScheme := Some("early-semver"),
    sonatypeProjectHosting := Some(GitHubHosting("mem-memov", "bingjvm", "mem-memov@yandex.ru")),

    // publish to the sonatype repository
    publishTo := sonatypePublishTo.value,


    scalaVersion := scala3Version,
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )




// https://docs.scala-lang.org/overviews/contributors/index.html
