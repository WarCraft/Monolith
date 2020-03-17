package gg.warcraft.monolith.api.player

import java.util.UUID

trait PlayerHidingService {
  def hide(id: UUID): Unit
  def hideFrom(id: UUID, from: UUID): Unit
  def hideFrom(id: UUID, predicate: Player => Boolean): Unit
  private[player] def hideAllFrom(id: UUID): Unit
  private[player] def updateHideFrom(id: UUID): Unit

  def reveal(id: UUID): Unit
  def revealTo(id: UUID, to: UUID): Unit
  private[player] def revealAllTo(id: UUID): Unit
}
