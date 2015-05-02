import sbt._
import sbt.Keys._

object ScalaProjectBuild extends Build {

  // Change _name to your project name.
  val _name = "scala-project"
  val _version = "1.0.0-SNAPSHOT"
  val _scalaVersion = "2.10.4"

  val sparkVersion = "1.3.1"
  val hadoopVersion = "2.3.0-cdh5.1.0"
  val excludeMortbayJetty = ExclusionRule(organization = "org.mortbay.jetty")
  val excludeServletApi   = ExclusionRule(organization = "javax.servlet")


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

        // Must run the examples and tests in separate JVMs to avoid mysterious
        // scala.reflect.internal.MissingRequirementError errors.
        // (https://issues.apache.org/jira/browse/SPARK-5281)
        // This should be removed when fixed in Spark SQL.
        fork := true,

        parallelExecution in Test := false,

        // Need to exclude slf4j-log4j12for logback to work.
        ivyXML :=
          <dependencies>
            <exclude org="org.slf4j" name="slf4j-log4j12"/>
          </dependencies>,

        // To remove warning about different versions of Scala (2.10.4, 2.10.5)
        ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) },

        libraryDependencies ++= Seq(

           "org.apache.spark"        %% "spark-core"                % sparkVersion % "provided"
            excludeAll(excludeServletApi, excludeMortbayJetty),

          "org.apache.spark"        %% "spark-sql"                 % sparkVersion,
          //  exclude("org.apache.hadoop", "hadoop-client"),



          "ch.qos.logback"           % "logback-classic"           % "1.1.2",
          "org.scalatest"           %% "scalatest"                 % "2.2.4"      % "test"
        )

      )
  )
    .settings(SCoverage.settings: _*)
    .settings(Assembly.settings: _*)
}
