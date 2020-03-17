package gg.warcraft.monolith.spigot

import org.bukkit.entity.Player
import org.bukkit.event.player.{PlayerInteractEvent, PlayerRespawnEvent}

package object player {
  type SpigotPlayer = Player

  type SpigotPlayerInteractEvent = PlayerInteractEvent
  type SpigotPlayerRespawnEvent = PlayerRespawnEvent
}
