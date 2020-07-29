lazy val commonSettings = Seq(
  organization := "gg.warcraft",
  version := "15.0.0-SNAPSHOT",
  scalaVersion := "2.13.3",
  scalacOptions ++= Seq(
    "-language:implicitConversions"
  ),
  resolvers ++= Seq(
    Resolver.mavenLocal
  )
)

lazy val assemblySettings = Seq(
  assemblyJarName in assembly := s"${name.value}-${version.value}-all.jar",
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", _ @_*) => MergeStrategy.discard
    case "module-info.class"         => MergeStrategy.discard
    case it                          => (assemblyMergeStrategy in assembly).value(it)
  }
)

lazy val commonDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.2.0" % Test
)

lazy val api = (project in file("monolith-api"))
  .settings(
    name := "monolith-api",
    commonSettings,
    libraryDependencies ++= commonDependencies ++ Seq(
      "org.flywaydb" % "flyway-core" % "6.5.2",
      "org.joml" % "joml" % "1.9.25",
      "org.xerial" % "sqlite-jdbc" % "3.32.3.2",
      "io.circe" %% "circe-core" % "0.13.0",
      "io.circe" %% "circe-generic" % "0.13.0",
      "io.circe" %% "circe-generic-extras" % "0.13.0",
      "io.circe" %% "circe-parser" % "0.13.0",
      "io.circe" %% "circe-yaml" % "0.13.1",
      "io.getquill" %% "quill-jdbc" % "3.5.2"
    )
  )

lazy val java = (project in file("monolith-java"))
  .settings(
    name := "monolith-java",
    commonSettings,
    assemblySettings
  )
  .dependsOn(api)

lazy val spigot = (project in file("monolith-spigot"))
  .settings(
    name := "monolith-spigot",
    commonSettings,
    assemblySettings,
    resolvers ++= Seq(
      "PaperMC" at "https://papermc.io/repo/repository/maven-public/"
    ),
    libraryDependencies ++= commonDependencies ++ Seq(
      "com.destroystokyo.paper" % "paper-api" % "1.15.2-R0.1-SNAPSHOT" % Provided
    )
  )
  .dependsOn(api)

lazy val bootstrap = (project in file("monolith-bootstrap"))
  .settings(
    name := "monolith-bootstrap",
    commonSettings,
    resolvers ++= Seq(
      "PaperMC" at "https://papermc.io/repo/repository/maven-public/"
    ),
    libraryDependencies ++= commonDependencies ++ Seq(
      "com.destroystokyo.paper" % "paper-api" % "1.15.2-R0.1-SNAPSHOT" % Provided,
      "io.circe" %% "circe-core" % "0.13.0",
      "io.circe" %% "circe-generic" % "0.13.0",
      "io.circe" %% "circe-parser" % "0.13.0",
      "io.circe" %% "circe-yaml" % "0.13.1"
    )
  )
