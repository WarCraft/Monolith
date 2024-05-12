lazy val projectSettings = Seq(
  organization := "gg.warcraft",
  version := "20.2.4-SNAPSHOT",
  scalaVersion := "2.13.12",
  scalacOptions ++= Seq(
    "-language:implicitConversions",
    "-Ypatmat-exhaust-depth",
    "10"
  ),
  resolvers ++= Seq(
    Resolver.mavenLocal
  )
)

lazy val assemblySettings = Seq(
  assembly / assemblyJarName := s"${name.value}-${version.value}-all.jar",
  assembly / assemblyMergeStrategy := {
    case PathList("META-INF", _ @_*) => MergeStrategy.discard
    case "module-info.class"         => MergeStrategy.discard
    case it                          => (assembly / assemblyMergeStrategy).value(it)
  }
)

val circeVersion = "0.14.6"
val jomlVersion = "1.10.+"

lazy val api = (project in file("monolith-api"))
  .settings(
    name := "monolith-api",
    projectSettings,
    libraryDependencies ++= Seq(
      "org.flywaydb" % "flyway-core" % "7.4.+",
      "org.joml" % "joml" % jomlVersion,
      "org.joml" % "joml-primitives" % jomlVersion,
      "org.postgresql" % "postgresql" % "42.5.+",
      "org.xerial" % "sqlite-jdbc" % "3.43.+",
      "io.circe" %% "circe-core" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-generic-extras" % "0.14.3",
      "io.circe" %% "circe-parser" % circeVersion,
      "io.circe" %% "circe-yaml" % "1.15.0",
      "io.getquill" %% "quill-jdbc" % "3.19.+"
    ) ++ Seq(
      "org.scalatest" %% "scalatest" % "3.2.+" % Test
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
      "io.papermc.paper" % "paper-api" % "1.20.2-R0.1-SNAPSHOT" % Provided
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
      "io.papermc.paper" % "paper-api" % "1.20.2-R0.1-SNAPSHOT" % Provided,
      "io.circe" %% "circe-core" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-parser" % circeVersion,
      "io.circe" %% "circe-yaml" % "1.15.0"
    )
  )
