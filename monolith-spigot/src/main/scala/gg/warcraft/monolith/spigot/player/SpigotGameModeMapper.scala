package gg.warcraft.monolith.spigot.player

import gg.warcraft.monolith.api.core.GameMode
import gg.warcraft.monolith.api.player.Player
import org.bukkit.{GameMode => SpigotGameMode}

class SpigotGameModeMapper {
  def map(mode: SpigotGameMode): Player.Mode = {
    case SpigotGameMode.ADVENTURE => GameMode.ADVENTURE
    case SpigotGameMode.CREATIVE  => GameMode.CREATIVE
    case SpigotGameMode.SPECTATOR => GameMode.SPECTATOR
    case SpigotGameMode.SURVIVAL  => GameMode.SURVIVAL
  }

  def map(mode: Player.Mode): SpigotGameMode = {
    case GameMode.ADVENTURE => SpigotGameMode.ADVENTURE
    case GameMode.CREATIVE  => SpigotGameMode.CREATIVE
    case GameMode.SPECTATOR => SpigotGameMode.SPECTATOR
    case GameMode.SURVIVAL  => SpigotGameMode.SURVIVAL
  }
}
