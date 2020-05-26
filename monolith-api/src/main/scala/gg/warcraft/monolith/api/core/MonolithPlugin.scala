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

import java.io.File
import java.util.logging.Logger
import java.util.Properties

import com.typesafe.config.ConfigFactory
import io.circe.yaml.parser
import io.circe.Decoder
import io.getquill.{
  H2Dialect, H2JdbcContext, MySQLDialect, MysqlJdbcContext, NamingStrategy,
  OracleDialect, OracleJdbcContext, PostgresDialect, PostgresJdbcContext,
  SqliteDialect, SqliteJdbcContext
}
import io.getquill.context.jdbc.JdbcContext
import io.getquill.context.sql.idiom.SqlIdiom
import org.flywaydb.core.Flyway

import scala.concurrent.ExecutionContext
import scala.io.Source

trait MonolithPlugin {
  private final val WARN_DEFAULT_CONFIG = "Falling back to default config!"
  private final val ERR_CONFIG_FAILED = "Failed to parse (default) config!"
  private final val separator = File.separator

  private[monolith] var databases: List[JdbcContext[_, _]] = Nil

  protected implicit val context: ExecutionContext = ExecutionContext.global

  protected def parseConfig[A: Decoder](config: String, fallback: Boolean = false)(
      implicit logger: Logger
  ): A = {
    def onError(err: io.circe.Error): A = {
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

  protected def initDatabase[D <: SqlIdiom, N <: NamingStrategy](
      dialect: D,
      naming: N,
      dataFolder: File
  ): JdbcContext[D, N] = {
    val databaseProps = new Properties()
    databaseProps.setProperty("driverClassName", "org.sqlite.JDBC")
    val databasePath = s"${dataFolder.getAbsolutePath}${separator}database.sqlite"
    databaseProps.setProperty("jdbcUrl", s"jdbc:sqlite:$databasePath")
    val databaseConfig = ConfigFactory.parseProperties(databaseProps)
    val database = dialect match {
      case _: H2Dialect       => new H2JdbcContext(naming, databaseConfig)
      case _: MySQLDialect    => new MysqlJdbcContext(naming, databaseConfig)
      case _: OracleDialect   => new OracleJdbcContext(naming, databaseConfig)
      case _: PostgresDialect => new PostgresJdbcContext(naming, databaseConfig)
      case _: SqliteDialect   => new SqliteJdbcContext(naming, databaseConfig)
    }
    databases ::= database
    database.asInstanceOf[JdbcContext[D, N]]
  }

  protected def upgradeDatabase(dataFolder: File, classLoader: ClassLoader)(implicit
      logger: Logger
  ): Unit = {
    val databasePath = s"${dataFolder.getAbsolutePath}${separator}database.sqlite"
    if (new File(databasePath).exists) {
      val flyway = Flyway
        .configure(classLoader)
        .dataSource(s"jdbc:sqlite:$databasePath", null, null)
        .load
      val migrations = flyway.migrate()
      logger.info(s"Applied $migrations new database schema migrations.")
    } else logger.warning(s"Failed to migrate $databasePath, it's missing.")
  }
}
