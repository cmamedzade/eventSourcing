name := "eventSourcing"

version := "0.1"

scalaVersion := "2.13.5"

val AkkaVersion = "2.6.14"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-persistence-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-persistence-testkit" % AkkaVersion % Test,
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-serialization-jackson" % "2.6.14",
  "ch.qos.logback" % "logback-classic" % "1.0.9",
  "org.slf4j" % "slf4j-api" % "1.7.30",
  "org.fusesource.leveldbjni" % "leveldbjni-all" % "1.8"
)