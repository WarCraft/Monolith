package gg.warcraft.monolith.spigot.world

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

package object item {
  type SpigotItemStack = ItemStack

  implicit class MaterialExtensions(val material: Material) {
    def contains(s: String): Boolean = material.name().contains(s)
    def startsWith(s: String): Boolean = material.name().startsWith(s)
    def endsWith(s: String): Boolean = material.name().endsWith(s)
    def is(affix: String, suffix: String): Boolean =
      material.name().startsWith(affix) && material.name().endsWith(suffix)
  }
}
