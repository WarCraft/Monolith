package gg.warcraft.monolith.spigot.world

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

package object item {
  type SpigotItemStack = ItemStack

  implicit class MaterialExtensions(val material: Material) {
    def head(s: String): Boolean = material.name().startsWith(s)
    def tail(s: String): Boolean = material.name().endsWith(s)
  }

  implicit class Regex(context: StringContext) {
    def r = new util.matching.Regex(
      context.parts.mkString,
      context.parts.tail.map(_ => "x"): _*
    )
  }
}
