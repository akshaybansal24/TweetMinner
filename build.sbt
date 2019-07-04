name := """Assignment2"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.6"

crossScalaVersions := Seq("2.11.12", "2.12.4")

libraryDependencies += guice

// Test Database
libraryDependencies ++= Seq(
  "com.h2database" % "h2" % "1.4.196",
  "org.assertj" % "assertj-core" % "3.6.2" % Test,
  "org.awaitility" % "awaitility" % "2.0.0" % Test,
  "org.twitter4j" % "twitter4j-async" % "4.0.4",
)
// Make verbose tests
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.14" % Test
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))

sources in (Compile, doc) ~= (_ filter (_.getName endsWith ".java"))
