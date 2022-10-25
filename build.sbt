name := "1Q-programming-test"

version := "0.0.1"

// AKKA DOCS
val AkkaVersion = "2.6.8"
val AkkaHttpVersion = "10.2.0"
lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "com.oneq",
      scalaVersion    := "2.12.12"
    )),
    name := "$name$",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"                % AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json"     % AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream"              % AkkaVersion,
      "ch.qos.logback"    % "logback-classic"           % "1.2.3",

      "com.typesafe.akka" %% "akka-http-testkit"        % AkkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion     % Test,
      "org.scalatest"     %% "scalatest"                % "3.0.8"         % Test,
      //"btomala" %% "akka-http-twirl" % "lastVersion"
    )
  )