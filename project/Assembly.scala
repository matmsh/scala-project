import sbt._
import Keys._
import sbtassembly.Plugin._
import AssemblyKeys._

object Assembly {

  val settings = {

    assemblySettings ++ Seq(
      test in assembly := {},
      mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
        {
          case m if m.toLowerCase.endsWith("manifest.mf") => MergeStrategy.discard
          case m if m.startsWith("META-INF") => MergeStrategy.discard
          case PathList("javax", "servlet", xs @ _*) => MergeStrategy.first
          case PathList("org", "apache", xs @ _*) => MergeStrategy.first
          case PathList("org", "jboss", xs @ _*) => MergeStrategy.first
          case "about.html" => MergeStrategy.rename
          case "reference.conf" => MergeStrategy.concat
          case _ => MergeStrategy.first
        }
      })
  }

}
