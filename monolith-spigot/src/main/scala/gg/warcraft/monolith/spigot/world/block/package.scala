package gg.warcraft.monolith.spigot.world

import org.bukkit.Material

package object block {
  implicit class MaterialExtensions(val material: Material) {
    def is(s: String): Boolean = material.name().startsWith(s)
  }
}
