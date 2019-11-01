lazy val commonSettings = Seq(
  organization := "gg.warcraft",
  version := "14.0.0-SNAPSHOT",
  scalaVersion := "2.13.1",
  scalacOptions ++= Seq(
    // additional scalac options go here
  ),
  resolvers ++= Seq(
    Resolver.mavenLocal,
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
  )
)

lazy val assemblySettings = Seq(
  assemblyJarName in assembly := s"${name.value}-${version.value}.jar",
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", it @ _*) => MergeStrategy.discard
    case "module-info.class"           => MergeStrategy.discard
    case it                            => (assemblyMergeStrategy in assembly).value(it)
  }
)

lazy val commonDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.0.8" % Test
)

lazy val api = (project in file("monolith-api"))
  .settings(
    name := "monolith-api",
    commonSettings,
    libraryDependencies ++= commonDependencies ++ Seq(
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.10.0",
      "com.google.guava" % "guava" % "28.1-jre",
      "com.google.inject" % "guice" % "4.2.3-SNAPSHOT",
      "com.google.inject.extensions" % "guice-assistedinject" % "4.2.3-SNAPSHOT",
      "io.circe" %% "circe-core" % "0.12.3",
      "io.circe" %% "circe-generic" % "0.12.3",
      "io.circe" %% "circe-parser" % "0.12.3",
      "org.joml" % "joml" % "1.9.19"
    )
  )

lazy val app = (project in file("monolith-app"))
  .settings(
    name := "monolith-app",
    commonSettings,
    libraryDependencies ++= commonDependencies ++ Seq(
      "com.fasterxml.jackson.core" % "jackson-core" % "2.10.0",
      "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % "2.10.0",
      "org.kohsuke" % "github-api" % "1.95",
      "redis.clients" % "jedis" % "3.1.0"
    )
  )
  .dependsOn(api)

lazy val spigot = (project in file("monolith-spigot"))
  .settings(
    name := "monolith-spigot",
    commonSettings,
    assemblySettings,
    libraryDependencies ++= commonDependencies ++ Seq(
      "org.spigotmc" % "spigot" % "1.14.4-R0.1-SNAPSHOT" % Provided,
      "de.slikey" % "EffectLib" % "6.3-SNAPSHOT" % Provided
    )
  )
  .dependsOn(app)
