package gg.warcraft.monolith.api.core

import io.getquill.{SnakeCase, SqliteDialect}
import io.getquill.context.jdbc.JdbcContext

object types {
  private[monolith] type DatabaseContext = JdbcContext[SqliteDialect, SnakeCase]
}
