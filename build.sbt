lazy val projectSettings = Seq(
  organization := "gg.warcraft",
  version := "16.0.0-SNAPSHOT",
  scalaVersion := "2.13.11",
  scalacOptions ++= Seq(
    "-language:implicitConversions"
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

val circeVersion = "0.14.2"
val jomlVersion = "1.10.+"

lazy val api = (project in file("monolith-api"))
  .settings(
    name := "monolith-api",
    projectSettings,
    libraryDependencies ++= Seq(
      "org.flywaydb" % "flyway-core" % "9.21.+",
      "org.joml" % "joml" % jomlVersion,
      "org.joml" % "joml-primitives" % jomlVersion,
      "org.postgresql" % "postgresql" % "42.5.+",
      "org.xerial" % "sqlite-jdbc" % "3.41.+",
      "io.circe" %% "circe-core" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-generic-extras" % circeVersion,
      "io.circe" %% "circe-parser" % circeVersion,
      "io.circe" %% "circe-yaml" % circeVersion,
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
      "com.destroystokyo.paper" % "paper-api" % "1.16.5-R0.1-SNAPSHOT" % Provided
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
      "com.destroystokyo.paper" % "paper-api" % "1.16.5-R0.1-SNAPSHOT" % Provided,
      "io.circe" %% "circe-core" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-parser" % circeVersion,
      "io.circe" %% "circe-yaml" % circeVersion
    )
  )
