
resolvers ++= Seq(
   "Akka Repository"        at "http://repo.akka.io/releases/",
   "Typesafe Repository"    at "http://repo.typesafe.com/typesafe/releases/",
    Resolver.url("artifactory", url("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"))
               (Resolver.ivyStylePatterns)   
)


addSbtPlugin("com.typesafe.sbteclipse"  % "sbteclipse-plugin"    % "3.0.0")

addSbtPlugin("com.eed3si9n"             % "sbt-assembly"         % "0.13.0")

addSbtPlugin("net.virtual-void"         % "sbt-dependency-graph" % "0.7.5")

addSbtPlugin("org.scoverage"            % "sbt-scoverage"        % "1.1.0")
