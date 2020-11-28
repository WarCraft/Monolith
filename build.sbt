lazy val projectSettings = Seq(
  organization := "gg.warcraft",
  version := "15.0.0-SNAPSHOT",
  scalaVersion := "2.13.4",
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

val circeVersion = "0.13.+"

lazy val api = (project in file("monolith-api"))
  .settings(
    name := "monolith-api",
    projectSettings,
    libraryDependencies ++= Seq(
      "org.flywaydb" % "flyway-core" % "6.5.+",
      "org.joml" % "joml" % "1.9.+",
      "org.xerial" % "sqlite-jdbc" % "3.32.+",
      "io.circe" % "circe-core_2.13" % circeVersion,
      "io.circe" % "circe-generic_2.13" % circeVersion,
      "io.circe" % "circe-generic-extras_2.13" % circeVersion,
      "io.circe" % "circe-parser_2.13" % circeVersion,
      "io.circe" % "circe-yaml_2.13" % circeVersion,
      "io.getquill" % "quill-jdbc_2.13" % "3.5.+"
    ) ++ Seq(
      "org.scalatest" % "scalatest_2.13" % "3.2.+" % Test
    )
  )

lazy val java = (project in file("monolith-java"))
  .settings(
    name := "monolith-java",
    projectSettings,
    assemblySettings
  )
  .dependsOn(api)

lazy val spigot = (project in file("monolith-spigot"))
  .settings(
    name := "monolith-spigot",
    projectSettings,
    assemblySettings,
    resolvers ++= Seq(
      "PaperMC" at "https://papermc.io/repo/repository/maven-public/"
    ),
    libraryDependencies ++= Seq(
      "com.destroystokyo.paper" % "paper-api" % "1.15.2-R0.1-SNAPSHOT" % Provided
    )
  )
  .dependsOn(api)

lazy val bootstrap = (project in file("monolith-bootstrap"))
  .settings(
    name := "monolith-bootstrap",
    projectSettings,
    resolvers ++= Seq(
      "PaperMC" at "https://papermc.io/repo/repository/maven-public/"
    ),
    libraryDependencies ++= Seq(
      "com.destroystokyo.paper" % "paper-api" % "1.15.2-R0.1-SNAPSHOT" % Provided,
      "io.circe" % "circe-core_2.13" % circeVersion,
      "io.circe" % "circe-generic_2.13" % circeVersion,
      "io.circe" % "circe-parser_2.13" % circeVersion,
      "io.circe" % "circe-yaml_2.13" % circeVersion
    )
  )
