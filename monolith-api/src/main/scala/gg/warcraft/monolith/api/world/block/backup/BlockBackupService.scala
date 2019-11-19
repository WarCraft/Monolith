package gg.warcraft.monolith.api.world.block.backup

import java.util.UUID

import gg.warcraft.monolith.api.world.BlockLocation

trait BlockBackupService {
  def createBackup(location: BlockLocation): UUID
  def restoreBackup(id: UUID): Boolean
  def restoreBackups(): Unit
}
