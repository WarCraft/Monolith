package gg.warcraft.monolith.api.core

import java.io.File
import java.util.logging.Logger
import java.util.Properties

import com.typesafe.config.ConfigFactory
import io.circe.yaml.parser
import io.circe.Decoder
import io.getquill.{SnakeCase, SqliteJdbcContext}
import io.getquill.context.jdbc.JdbcContext

import scala.concurrent.ExecutionContext
import scala.io.Source

trait MonolithPlugin {
  private final val WARN_DEFAULT_CONFIG = "Falling back to default config!"
  private final val ERR_CONFIG_FAILED = "Failed to parse (default) config!"

  private[monolith] var databases: List[JdbcContext[_, _]] = Nil

  protected implicit val context: ExecutionContext = ExecutionContext.global

  protected def parseConfig[A: Decoder](config: String, retry: Boolean = true)(
      implicit logger: Logger
  ): A = {
    def onError(err: io.circe.Error): A = {
      logger severe err.getMessage
      logger severe WARN_DEFAULT_CONFIG
      val defaultConfig = Source.fromResource("config.yml")
      if (retry) parseConfig(defaultConfig.mkString, retry = false)
      else throw new IllegalStateException(ERR_CONFIG_FAILED)
    }

    parser.parse(config) match {
      case Left(err) => onError(err)
      case Right(json) =>
        json.as[A] match {
          case Left(err)     => onError(err)
          case Right(config) => config
        }
    }
  }

  protected def initDatabase(dataFolder: File): JdbcContext[_, _] = {
    val databaseProps = new Properties()
    databaseProps.setProperty("driverClassName", "org.sqlite.JDBC")
    val databasePath = s"${dataFolder.getAbsolutePath}database.sqlite"
    databaseProps.setProperty("jdbcUrl", s"jdbc:sqlite:$databasePath")
    val databaseConfig = ConfigFactory.parseProperties(databaseProps)
    val database = new SqliteJdbcContext(SnakeCase, databaseConfig)
    databases ::= database
    database
  }
}
