package gg.warcraft.monolith.spigot

import org.bukkit.entity.Player
import org.bukkit.event.player.{PlayerInteractEvent, PlayerRespawnEvent}
import org.bukkit.OfflinePlayer

package object player {
  type SpigotPlayer = Player
  type OfflineSpigotPlayer = OfflinePlayer

  type SpigotPlayerInteractEvent = PlayerInteractEvent
  type SpigotPlayerRespawnEvent = PlayerRespawnEvent
}
