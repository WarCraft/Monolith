package gg.warcraft.monolith.api.core

import com.typesafe.config.Config
import io.getquill.{SnakeCase, SqliteJdbcContext}

import scala.concurrent.ExecutionContext

trait Repository {
  protected implicit val asyncCtx: ExecutionContext = ExecutionContext.global
  protected implicit val dbConfig: Config

  protected val db = new SqliteJdbcContext(SnakeCase, dbConfig)
}
