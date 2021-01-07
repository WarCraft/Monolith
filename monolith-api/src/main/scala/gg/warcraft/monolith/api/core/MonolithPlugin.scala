/*
 * MIT License
 *
 * Copyright (c) 2020 WarCraft <https://github.com/WarCraft>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package gg.warcraft.monolith.api.core

import com.typesafe.config.{Config, ConfigFactory}
import io.circe.yaml.parser
import io.circe.{Decoder, Error}
import io.getquill.context.jdbc.JdbcContext
import org.flywaydb.core.Flyway

import java.io.File
import java.util.Properties
import java.util.logging.Logger
import scala.io.Source
import scala.util.chaining._

trait MonolithPlugin {
  private final val WARN_DEFAULT_CONFIG = "Falling back to default config!"
  private final val ERR_CONFIG_FAILED = "Failed to parse (default) config!"
  private final val separator = File.separator

  // TODO collect repositories and add close method
  private[monolith] var databases: List[JdbcContext[_, _]] = Nil

  protected def parseConfig[A: Decoder](
      config: String,
      fallback: Boolean = false
  )(implicit
      logger: Logger
  ): A = {
    def onError(err: Error): A = {
      logger.severe(err.getMessage)
      if (fallback) {
        logger.severe(WARN_DEFAULT_CONFIG)
        val defaultConfig = Source.fromResource("config.yml")
        parseConfig(defaultConfig.mkString)
      } else throw new IllegalStateException(ERR_CONFIG_FAILED)
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

  protected def parseDatabaseConfig(
      config: DatabaseConfig,
      dataFolder: File
  ): Config =
    if (config.embedded) {
      val props = new Properties()
      props.setProperty("driverClassName", "org.sqlite.JDBC")
      val path = s"${dataFolder.getAbsolutePath}${File.separator}database.sqlite"
      props.setProperty("jdbcUrl", s"jdbc:sqlite:$path")
      ConfigFactory.parseProperties(props)
    } else config.postgres match {
      case Some(postgres) =>
        val props = new Properties()
        props.setProperty(
          "dataSourceClassName",
          "org.postgresql.ds.PGSimpleDataSource"
        )
        props.setProperty("dataSource.serverName", postgres.host)
        props.setProperty("dataSource.portNumber", postgres.port.toString)
        props.setProperty("dataSource.databaseName", postgres.database)
        props.setProperty("dataSource.user", postgres.user)
        props.setProperty("dataSource.password", postgres.password)
        props.setProperty("connectionTimeout", 30000.toString)
        ConfigFactory.parseProperties(props)
      case None => throw new IllegalStateException(
          "Embedded database is disabled, but Postgres config is missing."
        )
    }

  protected def upgradeDatabase(
      config: DatabaseConfig,
      dataFolder: File,
      classLoader: ClassLoader
  )(implicit
      logger: Logger
  ): Unit = {
    val flyway = Flyway
      .configure(classLoader)
      .pipe { it =>
        if (config.embedded) {
          val databasePath =
            s"${dataFolder.getAbsolutePath}${separator}database.sqlite"
          it.dataSource(s"jdbc:sqlite:$databasePath", null, null)
            .locations("classpath:/db/migration/sqlite")
        } else config.postgres match {
          case Some(postgres) =>
            it.dataSource(
              s"jdbc:postgresql://${postgres.host}:${postgres.port}/${postgres.database}",
              postgres.user,
              postgres.password
            ).locations("classpath:/db/migration/postgres")
          case None => throw new IllegalStateException(
              "Embedded database is disabled, but Postgres config is missing."
            )
        }
      }
      .load
    val migrations = flyway.migrate()
    logger.info(s"Applied $migrations new database schema migrations.")
  }
}
