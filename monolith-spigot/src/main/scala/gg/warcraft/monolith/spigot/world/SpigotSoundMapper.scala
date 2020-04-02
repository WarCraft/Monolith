package gg.warcraft.monolith.spigot.world

import gg.warcraft.monolith.api.world.{Sound, SoundCategory}
import org.bukkit.{Sound => SpigotSound, SoundCategory => SpigotSoundCategory}

class SpigotSoundMapper {
  def map(sound: SpigotSound): Sound =
    Sound.valueOf(sound.name())

  def map(sound: Sound): SpigotSound =
    SpigotSound.valueOf(sound.name())

  def map(category: SpigotSoundCategory): SoundCategory =
    SoundCategory.valueOf(category.name())

  def map(category: SoundCategory): SpigotSoundCategory =
    SpigotSoundCategory.valueOf(category.name())
}
