name := """cointracker-wallet"""
organization := "com.cointracker"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.13"

libraryDependencies += guice
libraryDependencies += "com.softwaremill.sttp.client3" %% "core" % "3.5.2"
libraryDependencies += "com.typesafe.play" %% "play-slick" % "5.0.0"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.12"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.7.3"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
