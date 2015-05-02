import sbt._
import sbt.Keys._

object ScalaProjectBuild extends Build {

  // Change _name to your project name.
  val _name = "scala-project"
  val _version = "1.0.0-SNAPSHOT"
  val _scalaVersion = "2.10.4"

  val akkaVersion = "2.3.10"
  val akkaStreamV = "1.0-RC2"


  lazy val scalaProject = Project(
    id = _name,
    base = file("."),

    settings =
      Defaults.coreDefaultSettings ++
        net.virtualvoid.sbt.graph.Plugin.graphSettings ++ Seq(
        name := _name,
        version := _version,
        scalaVersion := _scalaVersion,

        // To exclude generation of src/main/java, src/test/java
        unmanagedSourceDirectories in Compile <<= baseDirectory(base => (base / "src" / "main" / "scala") :: Nil),
        unmanagedSourceDirectories in Test <<= baseDirectory(base => (base / "src" / "test" / "scala") :: Nil),

        fork := false,

        parallelExecution in Test := false,

        // To remove warning about different versions of Scala (2.10.4, 2.10.5)
        ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) },

        libraryDependencies ++= Seq(


          "com.typesafe.akka" %% "akka-actor" % akkaVersion,
          "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
          "com.typesafe.akka" %% "akka-kernel" % akkaVersion,
          "com.typesafe.akka" %% "akka-remote" % akkaVersion,

          "com.typesafe.akka" %% "akka-stream-experimental"             % akkaStreamV,
          "com.typesafe.akka" %% "akka-http-core-experimental" % akkaStreamV,
          "com.typesafe.akka" %% "akka-http-scala-experimental" % akkaStreamV,
          "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaStreamV,

          "com.typesafe.akka" %% "akka-http-testkit-scala-experimental" % akkaStreamV % "test",


          "ch.qos.logback"           % "logback-classic"           % "1.1.2",
          "org.scalatest"           %% "scalatest"                 % "2.2.4"      % "test"
        )

      )
  )
    .settings(SCoverage.settings: _*)
    .settings(Assembly.settings: _*)
}
